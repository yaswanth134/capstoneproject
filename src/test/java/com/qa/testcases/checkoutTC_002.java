package com.qa.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.pages.CheckoutPage;

import GenericUtility.FileUtility;
import utilites.checkoutclass;


public class checkoutTC_002 extends checkoutclass {
	WebDriver driver;
    CheckoutPage checkoutPage;
	public FileUtility fUtils = new FileUtility();
    
    
    @BeforeMethod
    public void userIsOnTheCheckoutPage() throws IOException {
    	
    	String browser = fUtils.fetchDataFromPropertyFile("browser");
		String url = fUtils.fetchDataFromPropertyFile("url");
		
		if(browser.equals("chrome")) {
			System.setProperty("Webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.get(url); //  checkout URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        checkoutPage = new CheckoutPage(driver);
    }
    
    
    @Test(dataProvider = "checkoutData")
    public void checkoutProcess(String firstName1, String lastName1, String company1, String street1,
                                String city1, String state1, String zipCode1, String country1, String phone1,String paymentMethod) {
    	
    	
    	//test for checkout without login and accessing the project
    	test = extent.createTest("checkout Test", "Test for chechoutpage without login");
    	try {
    		
    		//selecting a product for men category and choosing jacket among them
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement men= driver.findElement(By.xpath("//*[@id=\"ui-id-5\"]"));
        	men.click();
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a")));
        	object.click();
        	WebElement size=driver.findElement(By.id("option-label-size-143-item-168"));
        	size.click();
        	WebElement color=driver.findElement(By.id("option-label-color-93-item-56"));
        	color.click();
        	
        	//adding product to cart
        	test.log(Status.INFO, "product got selected");
        	WebElement submit=driver.findElement(By.id("product-addtocart-button"));
        	submit.click();
        	
        	//confirmed product got added
        	test.log(Status.INFO, "product got added to cart");
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a")));
        	cartIcon.click();
        	WebElement viidicon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]")));
        	viidicon.click();
        	WebElement proceedtocheck=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/div/div/div/div[2]/div[3]/div/button")));
        	proceedtocheck.click();
        	
        	//navigated to checkout page and data filing
        	test.log(Status.INFO, "navigated to checkout page");
        	WebElement firstname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        	firstname.clear();
        	firstname.sendKeys(firstName1);
            WebElement lastNameField = driver.findElement(By.name("lastname"));
            lastNameField.clear();
            lastNameField.sendKeys(lastName1);
            WebElement companyField = driver.findElement(By.name("company"));
            companyField.sendKeys(company1);
            WebElement streetField = driver.findElement(By.name("street[0]"));
            streetField.sendKeys(street1);
            WebElement cityField = driver.findElement(By.name("city"));
            cityField.sendKeys(city1);
            WebElement stateDropdown = driver.findElement(By.name("region_id"));
            stateDropdown.sendKeys(state1);
            WebElement zipCodeField = driver.findElement( By.name("postcode"));
            zipCodeField.sendKeys(zipCode1);
            WebElement countryDropdown = driver.findElement( By.name("country_id"));
            countryDropdown.sendKeys(country1);
            WebElement phoneField = driver.findElement(By.name("telephone"));
            phoneField.sendKeys(phone1);
            System.out.println(paymentMethod);
            WebElement checkingok=driver.findElement(By.xpath("//input[@type='radio'][@value='"+paymentMethod+"']"));
            checkingok.click();
            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));
            continueButton.click();
            
            //checkout page displayed
        	test.log(Status.INFO, "checkout page details filled");
            // Add assertions here to verify successful checkout or any error messages
            // For example, check for an order confirmation message
            WebElement confirmationMessage = driver.findElement(By.id("customer-email-error"));
            assert confirmationMessage.isDisplayed() : "Checkout failed or confirmation message not found";
            test.log(Status.PASS, "checkout successfuly not processed");
    	}
    	catch(Exception exceotion){
    		System.out.println("asking for login");
    		exceotion.printStackTrace();
    	}
    }

    @DataProvider(name = "checkoutData")
    public Object[][] readExcelData() throws IOException {
        String excelFilePath = "C:\\Users\\Administrator\\Downloads\\data4.xlsx";
        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][columnCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        excelFile.close();

        return data;
    }
    
    @Test(dataProvider="checkoutData")
    public void missingshippingdeatils(String firstName1, String lastName1, String company1, String street1,
            String city1, String state1, String zipCode1, String country1, String phone1,String paymentMethod){
    	
    	//test for checkout with login and accessing the checkout but missing fields
    	test = extent.createTest("checkout Test", "Test for chechoutpage missing details with login");
    	try {

    		//logging in 
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
        	signin.click();
        	checkoutPage.login("daniel.recordo@example.com", "Password123"); 
        	test.log(Status.INFO, "logged in");
        	
        	//product got added 
        	WebElement men=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a")));
        	object.click();
        	WebElement size=driver.findElement(By.id("option-label-size-143-item-168"));
        	size.click();
        	WebElement color=driver.findElement(By.id("option-label-color-93-item-56"));
        	color.click();
        	WebElement submit2=driver.findElement(By.id("product-addtocart-button"));
        	submit2.click();
        	test.log(Status.INFO, "product got added");
        	
        	//payment selected and trying to miss some field and placing order
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a")));
        	cartIcon.click();
        	WebElement viidicon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]")));
        	viidicon.click();
        	WebElement proceedtocheck=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/div/div/div/div[2]/div[3]/div/button")));
        	proceedtocheck.click();
            System.out.println(paymentMethod);
            WebElement checkingok=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio'][@value='"+paymentMethod+"']")));
            checkingok.click();
            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));
            continueButton.click();
        	
        	 try {
 	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".field-error")));
 	            test.log(Status.INFO,"not placed successfuly");
 	        } catch (Exception e) {
 	            Assert.fail("Error message not displayed or page did not load correctly.");
 	            
 	        }
        	 test.log(Status.PASS, "not placed successfuly");
        	
    	}
    	catch(InvalidSelectorException exceotion){
    		System.out.println("asking for login");
    		exceotion.printStackTrace();
    	}
    }

    
    @Test(dataProvider = "checkoutData")
    public void checkoutProcess1(String firstName1, String lastName1, String company1, String street1,
                                String city1, String state1, String zipCode1, String country1, String phone1,String paymentMethod){
    	

    	//test for checkout with login and accessing the checkout
    	test = extent.createTest("checkout Test", "Test for chechoutpage with login");
    	try {
    		//logging in
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
        	signin.click();
        	checkoutPage.login("john.rajesh@example.com", "Password123"); 
        	test.log(Status.INFO, "logged in");
        	
        	//product got added 
        	WebElement men=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a")));
        	object.click();
        	WebElement size=driver.findElement(By.id("option-label-size-143-item-168"));
        	size.click();
        	WebElement color=driver.findElement(By.id("option-label-color-93-item-56"));
        	color.click();
        	WebElement submit2=driver.findElement(By.id("product-addtocart-button"));
        	submit2.click();
        	test.log(Status.INFO, "product got added");

        	//payment selected and trying to placing order
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a")));
        	cartIcon.click();
        	WebElement viidicon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]")));
        	viidicon.click();
        	WebElement proceedtocheck=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/div/div/div/div[2]/div[3]/div/button")));
        	proceedtocheck.click();
            System.out.println(paymentMethod);
            WebElement checkingok=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio'][@value='"+paymentMethod+"']")));
            checkingok.click();
            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));
            continueButton.click();
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"billing-address-same-as-shipping-checkmo\"]")));
            WebElement placetoorder=driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button"));
            placetoorder.click();
            WebElement orderedmsg=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span"));

        	test.log(Status.PASS, "order placed successfuly");
            // Add assertions here to verify successful checkout or any error messages
            // For example, check for an order confirmation message
            assert orderedmsg.isDisplayed();
    	}
    	catch(InvalidSelectorException exceotion){
    		System.out.println("asking for login");
    		exceotion.printStackTrace();
    	}
    }
   
    @Test(dataProvider = "checkoutData")
    public void addingnewadress(String firstName1, String lastName1, String company1, String street1,
                                String city1, String state1, String zipCode1, String country1, String phone1,String paymentMethod){
    	
    	//test for checkout with login and accessing the checkout with new address adding
    	test = extent.createTest("checkout Test", "Test for chechoutpage addnewaddress with login");
    	try {
    		//logged in
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
        	signin.click();
        	checkoutPage.login("john.rajesh@example.com", "Password123"); 
        	test.log(Status.INFO, "logged in");
        	
        	//added  a product
        	WebElement men=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a")));
        	object.click();
        	WebElement size=driver.findElement(By.id("option-label-size-143-item-168"));
        	size.click();
        	WebElement color=driver.findElement(By.id("option-label-color-93-item-56"));
        	color.click();
        	WebElement submit2=driver.findElement(By.id("product-addtocart-button"));
        	submit2.click();
        	test.log(Status.INFO, "product got added");
        	
        	//adding new address
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a")));
        	cartIcon.click();
        	WebElement viidicon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]")));
        	viidicon.click();
        	WebElement proceedtocheck=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/div/div/div/div[2]/div[3]/div/button")));
        	proceedtocheck.click();
        	WebElement newadress=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/div[2]/button")));
        	newadress.click();
        	WebElement firstname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        	firstname.clear();
        	firstname.sendKeys(firstName1);
            WebElement lastNameField = driver.findElement(By.name("lastname"));
            lastNameField.clear();
            lastNameField.sendKeys(lastName1);
            WebElement companyField = driver.findElement(By.name("company"));
            companyField.sendKeys(company1);
            WebElement streetField = driver.findElement(By.name("street[0]"));
            streetField.sendKeys(street1);
            WebElement cityField = driver.findElement(By.name("city"));
            cityField.sendKeys(city1);
            WebElement stateDropdown = driver.findElement(By.name("region_id"));
            stateDropdown.sendKeys(state1);
            WebElement zipCodeField = driver.findElement( By.name("postcode"));
            zipCodeField.sendKeys(zipCode1);
            WebElement countryDropdown = driver.findElement( By.name("country_id"));
            countryDropdown.sendKeys(country1);
            WebElement phoneField = driver.findElement(By.name("telephone"));
            phoneField.sendKeys(phone1);
            WebElement checkbox2=driver.findElement(By.xpath("/html/body/div[3]/aside[2]/div[2]/div/div/form/div/div[11]/input"));
            checkbox2.click();
            try {
            	
            	//found a bag while automating next button not clickable
	            WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.primary.action-save-address")));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
	            System.out.println(paymentMethod);
	            WebElement checkingok=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio'][@value='"+paymentMethod+"']")));
	            checkingok.click();
	            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));
	            continueButton.click();
	            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"billing-address-same-as-shipping-checkmo\"]")));
	            WebElement placetoorder=driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button"));
	            placetoorder.click();
	            WebElement orderedmsg=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span"));
            }
            catch(TimeoutException e) {
            	//order not placed
	        	test.log(Status.FAIL, "order placed successfuly");
	        	System.out.println("found bug");
            }
    	}
    	catch(InvalidSelectorException exceotion){
    		System.out.println("asking for login");
    		exceotion.printStackTrace();
    	}
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
