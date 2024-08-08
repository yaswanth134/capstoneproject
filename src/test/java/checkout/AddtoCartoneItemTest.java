package checkout;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.pages.addcartPage;

import GenericUtility.BaseClass;


public class AddtoCartoneItemTest extends BaseClass{
	@Test
	public void addtocardaction() throws InterruptedException, EncryptedDocumentException, IOException {
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
	
	

}
