package com.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;

    @FindBy(id = "firstname")
	public
    WebElement firstName;

    @FindBy(id = "lastname")
	public
    WebElement lastName;

    @FindBy(id = "email_address")
	public
    WebElement emailAddress;

    @FindBy(id = "password")
	public
    WebElement password;

    @FindBy(id = "password-confirmation")
	public
    WebElement confirmPassword;

    @FindBy(css = "button[title='Create an Account']")
    WebElement createAccountButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String fName, String lName, String email, String pwd, String confirmPwd) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        emailAddress.sendKeys(email);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(confirmPwd);
        createAccountButton.click();
    }

	public void RegistrationPage(String email, String password2) {
		// TODO Auto-generated method stub
		emailAddress.sendKeys(email); 
		password.sendKeys(password2);
		//registerButton.click();
		
	}
}
