package checkout;

import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.addcartPage;

import GenericUtility.BaseClass;

public class removeitemTest extends BaseClass{
	
	@Test
	public void addtocardaction() throws InterruptedException {
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
