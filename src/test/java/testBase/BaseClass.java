package testBase;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.v128.filesystem.model.File;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
public static WebDriver driver;
public Logger logger;
public Properties prop;

	
	
	@BeforeClass(groups={"Sanity","Regression", "Master"} )
	@Parameters({"os", "Browser"})
	public void setup(String os, String Browser) throws IOException {
		
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");	
		prop = new Properties();
		prop.load(file);
		
		
		
		logger = LogManager.getLogger(this.getClass());
		
		if(Browser.toLowerCase().equals("chrome")) {
			driver = new ChromeDriver();
		}
		
		else if (Browser.toLowerCase().equals("edge")) {
			driver = new EdgeDriver();
		}
		
		else if (Browser.toLowerCase().equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			return;
		}
		
		
		driver.get(prop.getProperty("appurl")); // adding url from properties file.
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression", "Master"} )
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		
		return generatedString;
		
	}
	
	public String randomNum() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNumeric;
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // SimpleDateFormat-- from java.text
		   																			  // Date() -- java.util
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;                   // File -- from java.io.file
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		
		return targetFilePath;
	}

}
