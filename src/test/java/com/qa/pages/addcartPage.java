package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class addcartPage {
	
	WebDriver driver;
	
	public addcartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@data-bind=\"scope: 'minicart_content'\"]")
	 private WebElement  aadcard_button;
	
	@FindBy(xpath="//span[@data-bind=\"text: getCartParam('summary_count')\"]")
	 private WebElement  totalnoofproduct;
	
	public WebElement getAadcard_button() {
		return aadcard_button;
	}
	
	public void clickadd() {
		aadcard_button.click();
	}

	public WebElement getTotalnoofproduct() {
		return totalnoofproduct;
	}
	public void checktotalnoofproduct() {
		System.out.println("totalno of product is  "   +totalnoofproduct.getText());
	}

}
