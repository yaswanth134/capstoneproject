package com.qa.cucumber;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilites.cucumbercheckout;

public class Checkoutpagesteps extends cucumbercheckout{
	WebDriver driver;
	CheckoutPage checkoutPage;
	
	@Given("User is on the checkout page")
    public void userIsOnTheCheckoutPage1() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/"); // replace with your checkout URL
        checkoutPage = new CheckoutPage(driver);
    }
    
    @And("User is on login page")
    public void userlogin() {
    	try {
    		checkoutPage.login("sai.varma@gamil.com", "SaiVarma1234");

            // Wait for the user  to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            try {
                wait.until(ExpectedConditions.titleContains("My Account"));
            } catch (Exception e) {
                Assert.fail("Login failed or page did not load correctly.");
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        
    }
    
    @And("User choose item")
    public void Userchooseitem() {
    	checkoutPage.item();
    }
    
    @And("User click on cart icon")
    public void clickoncart() {
    	checkoutPage.clickit();
    }

    @When("User enters the following shipping details")
    public void userEntersTheFollowingShippingDetails(DataTable dataT) {
        Map<String, String> data = dataT.asMap(String.class, String.class);
        checkoutPage.enterShippingAddress(data.get("FirstName"), data.get("LastName"), data.get("company"),
                data.get("street"), data.get("city"),data.get("state"), data.get("ZipCode"), data.get("Country"), data.get("Phone"));

    }

    @And("User selects the payment method Check  Money order {string}")
    public void userSelectsThePaymentMethod(String paymentMethod) {
        checkoutPage.selectPaymentMethod(paymentMethod);
    }

    @And("User confirms billing and shipping address are same")
    public void userConfirmsBillingAndShippingAddressAreSame() {
        checkoutPage.confirmBillingAndShippingSame();

    }

    @Then("User places the order")
    public void userPlacesTheOrder() {
        checkoutPage.placeOrder();
    }

    @And("Order is placed successfully")
    public void orderIsPlacedSuccessfully() {
        checkoutPage.verifyOrderSuccess();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Copy screenshot to desired location using FileUtils from Apache Commons IO library
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Administrator\\eclipse-workspace\\capstoneproject\\test-output\\screenshot.png"));
            System.out.println("Screenshot is saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        driver.quit();
    }
}
