package com.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.pages.whishlistpage;

import utilites.BaseTest;

public class whishlist extends BaseTest{
	
	WebDriver driver;
    whishlistpage wishpage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        wishpage=new whishlistpage(driver);
    }

    @Test(priority=1)
    public void testaddtoWishlist() {
    	test = extent.createTest("wishlist Test", "Test the wishlist without login process").assignCategory("smoke").assignCategory("regression").assignDevice("chrome");
    	try {
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		//category selected
        	WebElement men= driver.findElement(By.xpath("//*[@id=\"ui-id-5\"]"));
        	men.click();
        	test.log(Status.INFO, "started with category");
        	
        	//clothe type selected
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	
        	//preview
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a")));
        	object.click();
        	
        	//adding to list
        	WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtowishlist.click();
        	
	        try {
	        	wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")));
	            test.log(Status.PASS, "asking for login");
	        }
	        catch(Exception e) {
	        	Assert.fail(" error occured or page not lodded");
	        }
    	}
    	catch(Exception e) {
    		System.out.println("not siggned in");
    	}
    }
    
    @Test(priority=2)
    public void testaddtoWishlistwithlogin() {
    	test = extent.createTest("wishlist Test", "Test wishlist the login process");
    	try {
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
	        
    		//category selected
        	WebElement men= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	test.log(Status.INFO, "started with category");
        	
        	//clothe type selected
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	
        	//preview
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a")));
        	object.click();
        	
        	//adding to list
        	WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtowishlist.click();

        	test.log(Status.INFO, "added to wishlist");
        	
        	try {
        		WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
        		String Expcted="My Wish List";
        		Assert.assertEquals(Expcted,element.getText());

            	test.log(Status.PASS, "added to wishlist shown");
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	catch(Exception e) {
    		System.out.println("not siggned in or a page not logged");
    	}
    }

    @Test(priority=3)
    public void viewWishlistwithlogin() {
    	test = extent.createTest("wishlist Test", "view wishlist with the login process");
    	try {
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
	        
            WebElement asshown=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")));
            asshown.click();
            
    		//viewing wishlist
            WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[2]/a")));
            addtowishlist.click();
            test.log(Status.INFO, "viewing wishlist");
            
            //on wishlist
            try {
        		WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
        		String Expcted="My Wish List";
        		Assert.assertEquals(Expcted,element.getText());

            	test.log(Status.PASS, "successfuly viewed the wishlist");
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	catch(Exception e) {
    		System.out.println("not siggned in or a page not logged");
    	}
    }
    
    @Test(priority=4)
    public void removeWishlistwithlogin() throws InterruptedException {
    	test = extent.createTest("wishlist Test", "remove wishlist the login process");
    
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
	        
            WebElement asshown=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")));
            asshown.click();
            
    		//viewing wishlist
            WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[2]/a")));
            addtowishlist.click();
            test.log(Status.INFO, "viewing wishlist");
            
          
            
            WebElement removewishobject=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wishlist-sidebar\"]/li/div/div/div[2]/div[2]/a")));     
            removewishobject.click();
            
            //on remove wishlist
            try {
        		WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wishlist-view-form\"]/div[1]")));
        		String Expcted="You have no items in your wish list.";
        		Assert.assertEquals(Expcted,element.getText());

            	test.log(Status.PASS, "successfuly viewed the wishlist");
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	
    }
    
    @Test(priority=5)
    public void addduplicateWishlistwithlogin() throws InterruptedException {
    	test = extent.createTest("wishlist Test", "add duplicate wishlist the login process");
    
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
            
          //category selected
        	WebElement men= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	test.log(Status.INFO, "started with category");
        	
        	//clothe type selected
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	
        	//preview
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a")));
        	object.click();
        	
        	//adding to list
        	WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtowishlist.click();

        	test.log(Status.INFO, "added to wishlist");
        	
        	driver.navigate().back();
        	WebElement addtolist2= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtolist2.click();
        	
    		//viewing wishlist
        	try {
        		WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
        		String Expcted="My Wish List";
        		Assert.assertEquals(Expcted,element.getText());

            	test.log(Status.PASS, "added to wishlist shown");
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	
    }
    
    @Test(priority=6)
    public void addmultipleWishlistwithlogin() throws InterruptedException {
    	test = extent.createTest("wishlist Test", "add multiple wishlist the login process");
    
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
            
          //category selected
        	WebElement men= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-5\"]")));
        	men.click();
        	test.log(Status.INFO, "started with category");
        	
        	//clothe type selected
        	WebElement jackets= wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")));
        	jackets.click();
        	
        	//preview
        	WebElement object=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a")));
        	object.click();
        	
        	//adding to list
        	WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtowishlist.click();

        	test.log(Status.INFO, "added to wishlist");
        	
        	driver.navigate().back();
        	driver.navigate().back();
        	
        	//preview
        	WebElement object2=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[3]/div/a")));
        	object2.click();
        	
        	//adding to list
        	WebElement addtowishlist2=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]")));
        	addtowishlist2.click();
        	
        	try {
        		WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
        		String Expcted="My Wish List";
        		Assert.assertEquals(Expcted,element.getText());

            	test.log(Status.PASS, "added multiply to wishlist shown");
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    }
    
    @Test(priority=7)
    public void addWishlisttocartwithlogin() throws InterruptedException {
    	test = extent.createTest("wishlist Test", "remove wishlist the login process");
    
    		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
    		
    		WebElement signin=driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
    		signin.click();
    		
    		wishpage.login("john.rajesh@example.com", "Password123");
    		
            
            test.log(Status.INFO, "logged in successfuly");
            
            WebElement asshown=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")));
            asshown.click();
            
    		//viewing wishlist
            WebElement addtowishlist=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[2]/a")));
            addtowishlist.click();
            test.log(Status.INFO, "viewing wishlist");
            
            //on wishlist
            WebElement addtocart=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wishlist-sidebar\"]/li[1]/div/div/div[2]/div[1]/button")));
            addtocart.click();
        	
         
            WebElement size=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"option-label-size-143-item-167\"]")));
            size.click();
        	

            WebElement color=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"option-label-color-93-item-53\"]")));
            color.click();
            
            WebElement submit=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-addtocart-button\"]")));
            submit.click();
          
            try {
            	
            	WebElement element=wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
	    		String Expcted="My Wish List";
	    		Assert.assertEquals(Expcted,element.getText());
	
	        	test.log(Status.PASS, "added to cart from wishlist shown");
	    		

            	test.log(Status.PASS, "added multiply to wishlist shown");
            }catch(Exception e) {
        		e.printStackTrace();
        	}
    }
    
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
