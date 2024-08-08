package checkout;

import org.testng.annotations.Test;

import com.qa.pages.LoginPage;

import GenericUtility.BaseClass;

public class Loginaction extends BaseClass{
	
	@Test
	public void login() throws InterruptedException {
		LoginPage log =new LoginPage(driver);
		log.clicksign();
		log.loginaction("saifullah.pce@gmail.com", "S@ifullah786");
		
	}

}
