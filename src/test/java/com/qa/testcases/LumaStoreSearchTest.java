package com.qa.testcases;
import java.io.FileInputStream;import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.loginm;

import GenericUtility.FileUtility;

public class LumaStoreSearchTest { 
	
	WebDriver driver;

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
    }    
    
    @Test(dataProvider = "checkoutData")  
    public void checkoutProcess(String product) { 
    	 // Initialize WebDriverWait
       try {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      

    		
    		 WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

             // Enter search term in the search box
             searchBox.clear();
             searchBox.sendKeys(product);

             // Locate and click the search button
             WebElement searchButton = driver.findElement(By.xpath("//button[@title='Search']"));
             searchButton.click();

             // Wait until search results are displayed
             wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search.results")));

             // Verify search results are displayed
             WebElement searchResults = driver.findElement(By.cssSelector(".search.results"));
             assert searchResults.isDisplayed() : "Search results for product " + product + " are not displayed.";

             // Optionally, go back to the homepage or search page
             //driver.navigate().back();
       }catch(NullPointerException e) {
    	   System.out.println("''''''");
       }
    }
   @DataProvider(name = "checkoutData")    
   public Object[][] readExcelData() throws IOException {
	  
	        
	        
	   String excelFilePath = "C:\\Users\\Administrator\\Downloads\\SearchSheet.xlsx"; 
	   FileInputStream excelFile = new FileInputStream(excelFilePath);
       Workbook workbook1 = new XSSFWorkbook(excelFile); 
       Sheet sheet1 = workbook1.getSheet("Sheet2");
       int rowCount = sheet1.getPhysicalNumberOfRows();
       int columnCount = sheet1.getRow(0).getPhysicalNumberOfCells();
       Object[][] data = new Object[rowCount][columnCount];
       for (int i = 0; i < rowCount; i++) {
    	    Row row = sheet1.getRow(i);
    	    if (row != null) {
    	        for (int j = 0; j < columnCount; j++) {
    	            Cell cell = row.getCell(j);
    	            data[i][j] = (cell != null) ? cell.toString() : ""; // Handle null cells
    	        }
    	    }
    	}

       workbook1.close();     
       excelFile.close();
       return data;
   }
   
	@DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
            {"jacket", true},
            {"invalidQuery12345", false},
            {"", false},
            {"@#$%^&*", false},
            {"longsearchquerywithlotsofcharacters", false},
            {"'; DROP TABLE users;--", false},
            {"<script>alert('XSS')</script>", false}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testSearch(String query, boolean expectResults) {
        performSearchTest(query, expectResults);
    }

    private void performSearchTest(String query, boolean expectResults) {
        WebElement searchBar = driver.findElement((By) By.id("search"));
        searchBar.clear();
        searchBar.sendKeys(query);
        searchBar.submit();

        FluentWait<WebDriver> wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(10));
        WebElement results;

        try {
            results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.results")));
        } catch (Exception e) {
            results = null;
        } 

        if (expectResults) {
            Assert.assertNotNull(results, "Expected results for query: " + query);
        } else {
            Assert.assertTrue(results == null || results.getText().contains("Your search returned no results"), "Expected no results for query: " + query);
        }
    }
   @AfterMethod    public void tearDown() {
       if (driver != null)          
    	   driver.quit();
    	   
   }
}
