package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"})
	public void verifyAccountRegistration() throws InterruptedException {
		
		try {
		logger.info("***Starting TC001_AccountRegistrationTest***");
		
		HomePage hp = new HomePage(driver);
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on My accoumt link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		arp.setFirtsName(this.randomString().toUpperCase());
		arp.setLastName(this.randomString().toUpperCase());
		arp.setEmail(this.randomString() +"@gmail.com");
		arp.setTelephone(this.randomNum());
		String password = this.randomAlphaNumeric();
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		//Thread.sleep(3000);
		arp.setPrivacyPolicy();
		arp.clickContinueRegBtn();
		
		
		logger.info("Validating expected message..");
		
		//Assert.assertEquals(arp.getConfirmationMsg(), "Your Account Has Been Created!");
		
		// Above statement will fail the test and since it is hard assertion further execution is not completed 
		// and catch block is not executed. So we'll use if else
		if (arp.getConfirmationMsg().equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		
		}
		catch (Exception e){

			Assert.fail();
		}
		
		logger.info("***Finished TC001_AccountRegistrationTest***");
		
	}
	
	
}
