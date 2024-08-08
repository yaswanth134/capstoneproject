package com.qa.testcases;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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
import com.qa.pages.loginm;

import GenericUtility.FileUtility;
import checkout.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utilites.BaseTest;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;


public class loginTC_001 extends BaseTest{
    WebDriver driver;
    loginm loginPage;
    public FileUtility fUtils = new FileUtility();

    @BeforeMethod
    public void setUp() throws IOException {
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
        loginPage = new loginm(driver);
    }

    @Test
    public void testValidLogin() {
    	test = extent.createTest("login Test", "Test the login process").assignCategory("smoke").assignAuthor("yaswanth");
    	try {
	        loginPage.login("john.rajesh@example.com", "Password123");
	        test.log(Status.INFO, "loggin credinitals");
	
	
	        // Wait for the user dashboard to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        try {
	            wait.until(ExpectedConditions.titleContains("My Account"));
	            test.log(Status.PASS, "logged in successfuly");
	        } catch (Exception e) {
	            Assert.fail("Login failed or page did not load correctly.");
	            test.log(Status.FAIL, "login failed");
	        }
	
	        // Verify successful login by checking the page title or any other element
	        String expectedTitle = "My Account";
	        String actualTitle = driver.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle);
    	}
    	catch(Exception e) {
    		System.out.println("not siggned in");
    	}
    }
    
    @Test
    public void testIncorrectLogin() {

        test = extent.createTest("login Test", "Test the login process with incorrect email");
        try {
	        loginPage.login("incorrect.email@example.com", "Password123");
	        test.log(Status.INFO, "loggin credinitals");
	        // Wait for the error message to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-error")));
	            test.log(Status.INFO, "logged in unsuccessfuly");
	        } catch (Exception e) {
	            Assert.fail("Error message not displayed or page did not load correctly.");
	        }
	
	        // Verify the presence of the error message
	        WebElement errorMsg = driver.findElement(By.cssSelector(".message-error"));
	        Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed for invalid login.");
	        test.log(Status.PASS, "Did not logged in successfuly");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    @Test
    public void testIncorrectLogin_password() {
    	test = extent.createTest("login Test", "Test the login process with incorrect password");
    	try {
	        loginPage.login("john.rajesh@example.com", "invalidPassword");
	        test.log(Status.INFO, "loggin credinitals");
	        // Wait for the error message to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-error")));
	            test.log(Status.INFO, "logged in unsuccessfuly");
	        } catch (Exception e) {
	            Assert.fail("Error message not displayed or page did not load correctly.");
	        }
	
	        // Verify the presence of the error message
	        WebElement errorMsg = driver.findElement(By.cssSelector(".message-error"));
	        Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed for invalid login.");
	        test.log(Status.PASS, "Did not logged in successfuly");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testwrongcase() {
    	test = extent.createTest("login Test", "Test the login process with uppercase email,password");
    	try {
	        loginPage.login("JOHN.RAJESH@EXAMPLE.COM", "PASSWORD123");
	        test.log(Status.INFO, "loggin credinitals");
	        // Wait for the error message to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-error")));
	            test.log(Status.INFO, "logged in unsuccessfuly");
	        } catch (Exception e) {
	            Assert.fail("Error message not displayed or page did not load correctly.");
	        }
	
	        // Verify the presence of the error message
	        WebElement errorMsg = driver.findElement(By.cssSelector(".message-error"));
	        Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed for invalid login.");
	        test.log(Status.PASS, "Did not logged in successfuly");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testspecialcharcter() {
    	test = extent.createTest("login Test", "Test the login process with special charcter email password");
    	try {
	        loginPage.login("robert$downey@example.com","Password$113");
	        test.log(Status.INFO, "loggin credinitals");
	        // Wait for the error message to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        try {
	            wait.until(ExpectedConditions.titleContains("My Account"));
	            test.log(Status.INFO, "logged in successfuly");
	        } catch (Exception e) {
	            Assert.fail("login failed or page did not load correctly.");
	        }
	
	        // Verify successful login by checking the page title or any other element
	        String expectedTitle = "My Account";
	        String actualTitle = driver.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle);
	        test.log(Status.PASS, "logged in successfuly");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testempty_email() {
    	test = extent.createTest("login Test", "Test the login process with empty email");
    	try {
	        loginPage.login("","Password123");
	        test.log(Status.INFO, "loggin credinitals");
	        WebElement emailerror=driver.findElement(By.id("email-error"));
	        test.log(Status.INFO, "logged in unsuccessfuly");
	        Assert.assertEquals(emailerror.getText(), "This is a required field.");
	        test.log(Status.PASS, "logged in unsuccessfuly");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testempty_password() {
    	test = extent.createTest("login Test", "Test the login process with empty password");
    	try {
	        loginPage.login("jhon.rajesh@example.com","");
	        test.log(Status.INFO, "loggin credinitals");
	        WebElement passerror=driver.findElement(By.id("pass-error"));
	        test.log(Status.INFO, "logged in unsuccessfuly");
	        Assert.assertEquals(passerror.getText(), "This is a required field.");
	        test.log(Status.PASS, "logged in unsuccessfuly");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testforgottenpassword() {
    	test = extent.createTest("login Test", "Test the login process with forgotten password");
	    try {
	    	WebElement linkelemtn=driver.findElement(By.linkText("Forgot Your Password?"));
	    	linkelemtn.click();
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	 try {
	             wait.until(ExpectedConditions.titleContains("Forgot Your Password?"));
	 	        test.log(Status.INFO, "forgot password credinitals");
	         } catch (Exception e) {
	             Assert.fail("Error message not displayed or page did not load correctly.");
	         }
	    	 WebElement input=driver.findElement(By.name("email"));
	    	 input.sendKeys("john.rajesh@example.com");
	    	 WebElement button = driver.findElement(By.xpath("//button[@class='action submit primary']"));
	    	 button.click();
	    	 loginPage.login("john.rajesh@example.com", "Password123");
	         WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
	         try {

	 	        test.log(Status.INFO, "logged in successfuly");
	             wait2.until(ExpectedConditions.titleContains("My Account"));
	         } catch (Exception e) {
	             Assert.fail("Login failed or page did not load correctly.");
	         }
	
	         // Verify successful login by checking the page title or any other element
	         String expectedTitle = "My Account";
	         String actualTitle = driver.getTitle();
	         Assert.assertEquals(actualTitle, expectedTitle);

		        test.log(Status.PASS, "logged in with new password credinitals");
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
    
    @Test
	public void testinvalid_email() {
    	test = extent.createTest("login Test", "Test the login process without domain email");
    	try {
	        loginPage.login("johndasher","Password123");
	        test.log(Status.INFO, "submited credintals");
	        WebElement emailerror=driver.findElement(By.id("email-error"));
	        test.log(Status.INFO, "warning shown");
	        Assert.assertEquals(emailerror.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
	        test.log(Status.PASS, "succefuly error shown");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testinvalid_email2() {
    	test = extent.createTest("login Test", "Test the login process without domain email");
    	try {
	        loginPage.login("jhon,rajesh@example.com","Password123");
	        test.log(Status.INFO, "submited credintals");
	        WebElement emailerror=driver.findElement(By.id("email-error"));
	        test.log(Status.INFO, "successfuly not loggedin");
	        Assert.assertEquals(emailerror.getText(), "Please enter a valid email address.");
	        test.log(Status.PASS, "asking for porper email");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testinvalid_email3() {
    	test = extent.createTest("login Test", "Test the login process without emailname only domain name");
    	try {
	    	String Username="@example.com";
	    	String password="Password123";
	    	String list="";
	        WebElement usernamefield=driver.findElement(By.id("email"));
	        WebElement userpassfield=driver.findElement(By.id("pass"));
	        for(int i=0;i<Username.length();i++) {
	        		list="" +Username.charAt(i);
	        		usernamefield.sendKeys(list);
	        }
	        userpassfield.sendKeys(password);
	        WebElement login=driver.findElement(By.id("send2"));
	        login.click();
	        test.log(Status.INFO, "submited credintals");
	        WebElement emailerror=driver.findElement(By.id("email-error"));
	        test.log(Status.INFO, "successfuly not loggedin");
	        Assert.assertEquals(emailerror.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
	        test.log(Status.PASS, "asking for porper email");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testinvalid_email4() {
    	test = extent.createTest("login Test", "Test the login process only number");
    	try {
	        loginPage.login("12345","Password123");
	        test.log(Status.INFO, "submited credintals");
	        WebElement emailerror=driver.findElement(By.id("email-error"));
	        test.log(Status.INFO, "successfuly not loggedin");
	        Assert.assertEquals(emailerror.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
	        test.log(Status.PASS, "asking for porper email");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testinvalid_password() {
    	test = extent.createTest("login Test", "Test the login process with only empty password");
	    try {
	        loginPage.login("jhon.rajesh@example.com","    ");
	        test.log(Status.INFO, "submited credintals");
	        WebElement emailerror=driver.findElement(By.id("pass-error"));
	        test.log(Status.INFO, "successfuly not loggedin");
	        Assert.assertEquals(emailerror.getText(), "This is a required field.");
	        test.log(Status.PASS, "asking for porper password");
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
    
    @Test
    public void testinvalid_password1() {
    	test = extent.createTest("login Test", "Test the login process with only week password");
       try {
    	   loginPage.login("jhon.rajesh@example.com","yas");
	        test.log(Status.INFO, "submited credintals");
           WebElement emailerror=driver.findElement(By.id("pass-error"));
           Assert.assertEquals(emailerror.getText(), "Please enter a valid password.");
       }
       catch(Exception e) {
    	   System.out.println("accepting as a password");
	        test.log(Status.FAIL, "accepting a password");
       }
    }
    
    @Test
    public void testinvalid_password2() {
    	test = extent.createTest("login Test", "Test the login process with only continous one charcter password");
        try {
        	loginPage.login("jhon.rajesh@example.com","eeee");
	        test.log(Status.INFO, "submited credintals");
            WebElement emailerror=driver.findElement(By.id("pass-error"));
            Assert.assertEquals(emailerror.getText(), "Please enter a valid password.");
        }
        catch(Exception e) {
        	System.out.println("accepting as a password");
	        test.log(Status.FAIL, "accepting as a password");
        }
    }
    
    @Test(dataProvider = "loginData")
    public void loginUser(String email, String password) {
    	test = extent.createTest("login Test", "Test the login process with only continous one charcter password");
       try {
    	   if(email=="" && password=="") {
    		   test.log(Status.FAIL, "email and password is empty");
    	   }
    	   else {
    		   WebElement emailField = driver.findElement(By.id("email"));
               WebElement passwordField = driver.findElement(By.id("pass"));
               WebElement loginButton = driver.findElement(By.id("send2"));

               emailField.clear();
               passwordField.clear();

               emailField.sendKeys(email);
               passwordField.sendKeys(password);

    	        test.log(Status.INFO, "credintals extracted from excel");

               loginButton.click();

    	       test.log(Status.INFO, "submited credintals");
               // Add assertions here to verify login success or failure
              
               WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
               try {
                   wait.until(ExpectedConditions.titleContains("My Account"));

       	        test.log(Status.INFO,"logged in");
               } catch (Exception e) {
                   Assert.fail("Login failed or page did not load correctly.");
               }

               // Verify successful login by checking the page title or any other element
               String expectedTitle = "My Account";
               String actualTitle = driver.getTitle();
               Assert.assertEquals(actualTitle, expectedTitle);

    	        test.log(Status.PASS, "logged in successfuly");
    	   }
       }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
    }

    @DataProvider(name = "loginData")
    public Object[][] readExcelData() throws IOException {
        String excelFilePath = "C:\\Users\\Administrator\\Downloads\\data2.xlsx";
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
    
    @Test
    public void loginUser() {
    	test = extent.createTest("login Test", "Test the login process");
    	try {
	        // Create a new LoginRequest object
	        LoginRequest loginRequest = new LoginRequest();
	        loginRequest.setUsername("srinag.doe@example.com");  // Replace with actual username
	        loginRequest.setPassword("Password123");  // Replace with actual password
	        test.log(Status.INFO, "loggin credinitals");
	
	        // Send the POST request to the login endpoint
	        int statusCode = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(loginRequest)
	                .post("https://magento.softwaretestingboard.com/rest/V1/integration/customer/token")
	                .getStatusCode();
	     // Print username, password, and status code to the console
	        System.out.println("Username: " + loginRequest.getUsername());
	        System.out.println("Password: " + loginRequest.getPassword());
	        System.out.println("Status Code: " + statusCode);
	        // Print and verify the status code
	        System.out.println(statusCode);
            test.log(Status.INFO, "logged in successfuly");
	        assertEquals(200, statusCode);  // Assert that the status code is 200 for successful login
            test.log(Status.PASS, "api extracted");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
