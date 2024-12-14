package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verifyLoginDDT(String email, String pwd, String exp ) {
		try {
			logger.info("****Starting TC003_LoginDDT******");
			
			HomePage hp = new HomePage(driver);
			LoginPage lp = new LoginPage(driver);
			MyAccountPage mp = new MyAccountPage(driver);
			
			hp.clickMyAccount(); logger.info("Clicked on My account link");
			hp.clickLogin(); logger.info("Clicked on Login link");
			
			lp.setEmail(email); logger.info("Email entered");
			lp.setpassword(pwd); logger.info("Password entered");
			lp.clickLoginButton(); logger.info("Clicked on Login");
			
			boolean result = mp.validateMyAccount();
			
			
			/* data is valid - login success - test pass - logout
								login failed - test fail
			
			   data is invalid - login success - test fail - logout
			                     login failed - test pass
			
			*/
			
			
			if(exp.equalsIgnoreCase("valid")) {
				if(result==true) {
					mp.clickLogoutButton();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Invalid")){
				if(result==true) {
					mp.clickLogoutButton();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
		catch (Exception e) {
			Assert.fail();
		}

		logger.info("****Starting TC003_LoginDDT******");
	}

}
