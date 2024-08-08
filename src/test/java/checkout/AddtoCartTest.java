package checkout;

import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

import GenericUtility.BaseClass;


public class AddtoCartTest extends BaseClass{
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
	
	

}
