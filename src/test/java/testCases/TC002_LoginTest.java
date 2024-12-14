package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups= {"Sanity","Master"})
	public void verifyLogin() {
		try {
		    logger.info("****Starting TC002_LoginTest******");
			
			HomePage hp = new HomePage(driver);
			LoginPage lp = new LoginPage(driver);
			MyAccountPage mp = new MyAccountPage(driver);
			
			hp.clickMyAccount(); logger.info("Clicked on My account link");
			hp.clickLogin(); logger.info("Clicked on Login link");
			
			lp.setEmail(prop.getProperty("email")); logger.info("Email entered");
			lp.setpassword(prop.getProperty("password")); logger.info("Password entered");
			lp.clickLoginButton(); logger.info("Clicked on Login");
			
			Assert.assertTrue(mp.validateMyAccount());
		}
		catch (Exception e) {
			Assert.fail();
		}
			
			logger.info("****Finished TC002_LoginTest******");
		
		}

}


