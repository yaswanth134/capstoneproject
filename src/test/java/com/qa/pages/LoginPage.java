package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="(//a[contains(text(), 'Sign In')])[1]")
	 private WebElement signinbutton;
	
	@FindBy(id="email")
	 private WebElement email_textfield;
	
	@FindBy(id="pass")
	 private WebElement Password_textfield;
	
	@FindBy(xpath="//span[text()='Sign In']")
	private WebElement sign_button;

	public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
//	public WebElement getSigninbutton() {
//		return signinbutton;
//	}
//
//	public WebElement getEmail_textfield() {
//		return email_textfield;
//	}
//
//	public WebElement getPassword_textfield() {
//		return Password_textfield;
//	}
//
//	public WebElement getSign_button() {
//		return sign_button;
//	}
    public void clicksign() {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement signin=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a")));
    	signin.click();
    }
    public void loginaction(String email,String password) throws InterruptedException {
    	
    	email_textfield.sendKeys(email);
    	Password_textfield.sendKeys(password);
    	sign_button.click();
    }
    
}
