package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	By txtFirstName = By.xpath("//input[@id='input-firstname']");
	By txtLastName = By.xpath("//input[@id='input-lastname']");
	By txtEmail = By.xpath("//input[@id='input-email']");
	By txtTelephone = By.xpath("//input[@id='input-telephone']");
	By txtPassword = By.xpath("//input[@id='input-password']");
	By txtConfirmPassword = By.xpath("//input[@id='input-confirm']");
	By btnSubscribe = By.xpath("//label[normalize-space()='Yes']");
	By chkAgreePvc = By.xpath("//input[@name='agree']");
	By btnContinueRegisterButton = By.xpath("//input[@value='Continue']");
	By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
	
	public void setFirtsName (String fName) {
		driver.findElement(txtFirstName).sendKeys(fName);
	}
	
	public void setLastName (String lName) {
		driver.findElement(txtLastName).sendKeys(lName);
	}
	
	public void setEmail (String eMailId) {
		driver.findElement(txtEmail).sendKeys(eMailId);
	}
	
	public void setTelephone(String tel) {
		driver.findElement(txtTelephone).sendKeys(tel);
	}
	
	
	
	public void setPassword (String pwd) {
		driver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setConfirmPassword (String conPwd) {
		driver.findElement(txtConfirmPassword).sendKeys(conPwd);
	}
	
	public void setSubscription() {
		driver.findElement(btnSubscribe).click();
	}
	
	public void setPrivacyPolicy() {
		driver.findElement(chkAgreePvc).click();
	}
	
	public void clickContinueRegBtn() {
		driver.findElement(btnContinueRegisterButton).click();
	}
	
	public String getConfirmationMsg () {
		try {
			return driver.findElement(msgConfirmation).getText();
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	
	
}
