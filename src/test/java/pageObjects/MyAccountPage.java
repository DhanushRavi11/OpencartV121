package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{
	public MyAccountPage (WebDriver driver) {
		super(driver);
	}
	
	By myAccountText = By.xpath("//h2[normalize-space()='My Account']");
	By logoutButton = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
	
	public boolean validateMyAccount() {
		
		try {
			return (driver.findElement(myAccountText).isDisplayed()); 	
		}
		
		catch (Exception e) {
			return false;
		}
		
	}
	
	public void clickLogoutButton() {
		driver.findElement(logoutButton).click();
	}

}
