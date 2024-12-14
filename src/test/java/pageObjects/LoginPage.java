package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	By eMail = By.xpath("//input[@id='input-email']");
	By password = By.xpath("//input[@id='input-password']");
	By loginButton = By.xpath("//input[@value='Login']");
	
	
	public void setEmail(String email) {
		driver.findElement(eMail).sendKeys(email);
	}
	
	public void setpassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	


}
