package com.qa.testcases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.pages.addcartPage;

import GenericUtility.BaseClass;
import org.openqa.selenium.WebDriver;


public class addtocart extends BaseClass{
	
	//WebDriver driver;
	
//	@Test
//	public void login() throws InterruptedException {
//		LoginPage log =new LoginPage(driver);
//		log.loginaction("saifullah.pce@gmail.com", "S@ifullah786");
//		log.clicksign();
//		
//	}
	
	//getting product display
	@Test
	public void addtocardaction() throws InterruptedException {
		LoginPage log =new LoginPage(driver);
		log.clicksign();
		log.loginaction("saifullah.pce@gmail.com", "S@ifullah786");
		DashboardPage dash =new DashboardPage(driver);
		dash.clickwomen(driver);	
		dash.clicktop(driver);
		dash.clickjeans(driver);
		Thread.sleep(2000);
		dash.getLightjacket();
	
	}
	
	//adding product to cart
	@Test
	public void addtocardaction1() throws InterruptedException, EncryptedDocumentException, IOException {
		
		String email = fUtils.fetchStringDataFromExcelFile("login", 1, 0);
		String password = fUtils.fetchStringDataFromExcelFile("login", 1, 1);
	
		LoginPage log =new LoginPage(driver);
		log.clicksign();
		log.loginaction(email, password);
		DashboardPage dash =new DashboardPage(driver);
		dash.clickwomen(driver);	
		dash.clicktop(driver);
		dash.clickjeans(driver);
		Thread.sleep(2000);
		dash.lightjacket(driver);
		dash.clickaddtocart();
	  addcartPage add=new addcartPage(driver);
	  add.clickadd();
	  add.checktotalnoofproduct();
	}
	
	//adding multiple items to cart
	@Test
	public void addtocardaction2() throws InterruptedException {
	
	DashboardPage dash =new DashboardPage(driver);
	dash.clickwomen(driver);	
	dash.clicktop(driver);
	dash.clickjeans(driver);
	Thread.sleep(2000);
	dash.lightjacket(driver);
	dash.clickaddtocart();
	 addcartPage add=new addcartPage(driver);
	  add.clickadd();
	  add.checktotalnoofproduct();
	  
	dash.clickjackettext();
	dash.clicknavejacket();
	dash.clickaddtocart();
	
	  add.clickadd();
	  add.checktotalnoofproduct();
	dash.clickjackettext();
	dash.clicknadiasheel();
	dash.clickaddtocart();
	 add.clickadd();
	  add.checktotalnoofproduct();
	
	}
	
	//remove the product from cart
	@Test
	public void addtocardaction4() throws InterruptedException {
		DashboardPage dash =new DashboardPage(driver);
		dash.clickwomen(driver);	
		dash.clicktop(driver);
		dash.clickjeans(driver);
		Thread.sleep(2000);
		dash.lightjacket(driver);
		dash.clickaddtocart();
		 addcartPage add=new addcartPage(driver);
		  add.clickadd();
		  add.checktotalnoofproduct();
		  
		dash.clickjackettext();
		dash.clicknavejacket();
		dash.clickaddtocart();
		
		  add.clickadd();
		  add.checktotalnoofproduct();
		dash.clickjackettext();
		dash.clicknadiasheel();
		dash.clickaddtocart();
		 add.clickadd();
		  add.checktotalnoofproduct();
		
		// add some code
		
		
		dash.clickdeletebutton();
		dash.clickdeletebutton();
		dash.clickdeletebutton();
		

	}
}
