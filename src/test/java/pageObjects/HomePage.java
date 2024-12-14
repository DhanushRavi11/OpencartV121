package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	By myAccount = By.xpath("//a[@title='My Account']");
	By register = By.xpath("//a[normalize-space()='Register']");
	By login = By.xpath("//a[normalize-space()='Login']");
	
	public void clickRegister() {
		driver.findElement(register).click();
	}
	
	public void clickMyAccount() {
		driver.findElement(myAccount).click();
	}
	
	public void clickLogin() {
		driver.findElement(login).click();
	}
	

} 