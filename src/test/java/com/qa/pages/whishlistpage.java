package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class whishlistpage {
	
	WebDriver driver;

    @FindBy(id = "email")
    WebElement emailAddress;

    @FindBy(id = "pass")
    WebElement password;

    @FindBy(id = "send2")
    WebElement loginButton;
	
	
	public whishlistpage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	public void login(String email, String pwd) {
        emailAddress.sendKeys(email);
        password.sendKeys(pwd);
        loginButton.click();
    }
}
