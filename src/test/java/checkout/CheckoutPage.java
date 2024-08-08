package checkout;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utilites.cucumbercheckout;

public class CheckoutPage{
	WebDriver driver;
	

    @FindBy(id = "email")
    WebElement emailAddress;

    @FindBy(id = "pass")
    WebElement password;

    @FindBy(id = "send2")
    WebElement loginButton;

   
    
    By lastNameField = By.name("lastname");
    By company = By.name("company");
    By StreetAddress  = By.name("street[0]");
    By City = By.name("city");
    By state = By.name("region_id");
    By postalcode = By.name("postcode");
    By country = By.name("country_id");
    By phoneno=By.name("telephone");
    By next = By.cssSelector("button action continue primary");
    By checkingok=By.name("billing-address-same-as-shipping");
    By placeOrderButton=By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button");
    By orderSuccessMessage = By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void login(String email, String pwd) {
    	try {

            emailAddress.sendKeys(email);
            password.sendKeys(pwd);
            loginButton.click();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void clickit() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".counter.qty")));
    	cartIcon.click();
    	WebElement activeCartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("top-cart-btn-checkout")));
    	activeCartIcon.click();
   	 	try {
            wait.until(ExpectedConditions.titleContains("Checkout"));
        } catch (Exception e) {
            Assert.fail("Error message not displayed or page did not load correctly.");
        }
    }
    public void enterShippingAddress(String firstName, String lastName, String company1, String street, String city, String state1, String ZipCode, String Country1, String Phone) {
	    try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	WebElement firstname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
	    	firstname.clear();
	    	firstname.sendKeys(firstName);
	    	WebElement lastnamekey= driver.findElement(By.name("lastname"));      
	    	lastnamekey.clear();
	    	lastnamekey.sendKeys(lastName);
	    	driver.findElement(company).sendKeys(company1);
	        driver.findElement(StreetAddress).sendKeys(street);
	        driver.findElement(City).sendKeys(city);
	        WebElement selector= driver.findElement(state);
	        Select select=new Select(selector);
	        select.selectByVisibleText(state1);
	        driver.findElement(postalcode).sendKeys(ZipCode);
	        driver.findElement(phoneno).sendKeys(Phone);
	        WebElement selector2= driver.findElement(country);
	        Select select2=new Select(selector2);
	        select2.selectByVisibleText(Country1);
	    }
	    catch(Exception e) {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	WebElement firstname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-step-shipping\"]/div[1]/div/div/div")));
	    	
	    }

    }

    public void selectPaymentMethod(String paymentMethod) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement paymentMethodOption= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[1]/table/tbody/tr[2]/td[1]/input")));//ko_unique_4
    	paymentMethodOption.click();
    }

    public void confirmBillingAndShippingSame() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement paymentMethodOption= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")));//ko_unique_4
       paymentMethodOption.click();
    }

    public void placeOrder() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement checkbox= wait.until(ExpectedConditions.visibilityOfElementLocated(checkingok));
        driver.findElement(placeOrderButton).click();
    }
    public void verifyOrderSuccess() {
        assert driver.findElement(orderSuccessMessage).isDisplayed();
        
    }

	public void item() {
		// TODO Auto-generated method stub
		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
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
	}
}
