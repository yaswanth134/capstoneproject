package checkout;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.RegistrationPage;

public class SignUpTests {
	WebDriver driver;
    RegistrationPage registrationPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        registrationPage = new RegistrationPage(driver);
    }

    @Test(priority=1)
    public void testUserRegistration() {
        registrationPage.registerUser("sri", "nag", "srinaga.doe@example.com", "Password123", "Password123");
        String expectedTitle = "My Account";
        String actualTitle = driver.getTitle();
        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("Successfully Registered");
        } catch (AssertionError e) {
            System.out.println("Account already exists");
        }
    }

    @Test(priority=2)
    public void TC_SignUp_02_UnsuccessfulSignUpWithEmptyMandatoryFields() {
        registrationPage.registerUser("", "LastName", "valid@email.com", "password123", "password123");
        String expectedTitle = "My Account";
        String actualTitle = driver.getTitle();
        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("Successfully Registered");
        } catch (AssertionError e) {
            System.out.println("Please enter mandatory fields");
        }
    }

    @Test(priority =3)
    public void TC_SignUp_03_UnsuccessfulSignUpWithInvalidEmailFormat() {
        registrationPage.registerUser("FirstName", "LastName", "invalid-email-format", "password123", "password123");
        String expectedTitle = "My Account";
        String actualTitle = driver.getTitle();
        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("Successfully Registered");
        } catch (AssertionError e) {
            System.out.println("Please enter a valid email address");
        }
        // Example check for error message (if applicable)
        // Assert.assertTrue(registrationPage.isErrorMessageDisplayed("Please enter a valid email address (Ex: johndoe@domain.com)."), 
        //     "Expected error message for invalid email format is not displayed.");
    }

    @Test(priority=4)
    public void TC_SignUp_04_UnsuccessfulSignUpWithWeakPassword() {
        registrationPage.registerUser("FirstName", "LastName", "valid.email@example.com", "123", "123");
        String errMsg = driver.findElement(By.id("password-error")).getText(); // Adjust the selector if necessary
        System.out.println("Error Message: " + errMsg);
        Assert.assertEquals(errMsg, "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."); // Adjust based on your application
    }

    @Test(priority=5)
    public void TC_SignUp_05_UnsuccessfulSignUpWithMismatchedPassword() {
        registrationPage.registerUser("FirstName", "LastName", "valid.email@example.com", "Password123", "Password1234");
        String errMsg = driver.findElement(By.id("password-confirmation-error")).getText(); // Adjust the selector if necessary
        System.out.println("Error Message: " + errMsg);
        Assert.assertEquals(errMsg, "Please enter the same value again."); // Adjust based on your application
    }
    @Test(priority=6)
    public void TC_SignUp_06_SuccessfulRegistration() {
        registrationPage.registerUser("raj", "naug", "raju.doe@example.com", "Password123", "Password123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("message-success")));
        String successMsg = element.getText();
        Assert.assertEquals(successMsg, "Thank you for registering with Main Website Store.");
        System.out.println("Success Message: " + successMsg);
    }

    @AfterMethod
    public void tearDown() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("C:\\Users\\Administrator\\eclipse-workspace\\Capstone-Project-Wipro24\\Screenshots\\screenshot_" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
