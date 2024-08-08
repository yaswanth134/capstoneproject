package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e']")
	 private WebElement Women_dropdownbutton;
	
	@FindBy(xpath="//span[text()='Tops']")
	 private WebElement Top_button;
	
	@FindBy(xpath="//span[text()='Jackets']")
	 private WebElement jacket_button;
	
	@FindBy(xpath="//a[contains(text(), 'Olivia 1/4 Zip Light Jacket ')]")
	 private WebElement lightjacket_button;
	
	@FindBy(xpath="//li[@class='item product product-item']")
	 private WebElement product;
	
	@FindBy(xpath="//div[text()='XS']")
	 private  WebElement size;
	
	@FindBy(xpath="//div[@option-label='Black']")
	 private  WebElement colure;
	
	@FindBy(xpath="//span[text()='Add to Cart']")
	 private  WebElement addtocart;
	
	public WebElement getLightjacket() {
		return lightjacket_button;
	}

	public WebElement getJacket_button() {
		return jacket_button;
	}
	
	public WebElement getTop_button() {
		return Top_button;
	}
	public WebElement getWomen_dropdownbutton() throws InterruptedException {
		Thread.sleep(2000);
		return Women_dropdownbutton;
	}
	public void clickwomen(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(Women_dropdownbutton).perform();
		//Women_dropdownbutton.click();
}
	public void clicktop(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(Top_button).perform();
		//Top_button.click();
	}
	public void clickjeans(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(jacket_button).perform();
		jacket_button.click();
	}
	public void clickpro() {
		product.click();
	}
	public void lightjacket(WebDriver driver) throws InterruptedException {
		//Actions action = new Actions(driver);
		//action.moveToElement(lightjacket_button).perform();
		Thread.sleep(2000);
		lightjacket_button.click();
	}
	public void clickaddtocart() throws InterruptedException {
		size.click();
		Thread.sleep(2000);
		colure.click();
		Thread.sleep(2000);
		addtocart.click();
	}

	public WebElement getLightjacket_button() {
		return lightjacket_button;
	}

	public WebElement getProduct() {
		return product;
	}

	public WebElement getSize() {
		return size;
	}

	public WebElement getColure() {
		return colure;
	}

	public WebElement getAddtocart() {
		return addtocart;
	}
	//new case2
		@FindBy(xpath="//a[text()='Jackets']")
		 private WebElement jacket;
		
		@FindBy(xpath="//a[contains(text(), 'Neve Studio Dance Jacket')]")
		 private WebElement nevejacket;
		
		@FindBy(xpath="//a[contains(text(), 'Nadia Elements Shell ')]")
		 private WebElement	nadiasheel;

		public WebElement getJacket() {
			return jacket;
		}

		public WebElement getNevejacket() {
			return nevejacket;
		}

		public WebElement getNadiasheel() {
			return nadiasheel;
		}
		public void clickjackettext() {
			jacket.click();
		}
		public void clicknavejacket() {
			nevejacket.click();
		}
		public void clicknadiasheel() {
			nadiasheel.click();
		}
		//case3 
		@FindBy(xpath="//a[@title='Remove item']")
		private WebElement delete_button;

		public WebElement getDelete_button() {
			return delete_button;
		}

		public void clickdeletebutton() {
			delete_button.click();
		}
}