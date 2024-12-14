package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // Populate common info
	public ExtentTest test; // Creating test case entries and update status of results
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName= "Test-Report-" + timeStamp + ".html";
	    sparkReporter= new ExtentSparkReporter(".\\reports\\" + repName);
	    
	    sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
	    sparkReporter.config().setReportName("OpenCart Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);
	    
	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Application", "OpenCart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("SubModule", "Customers");
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    
	    String os = testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    
	    String browser = testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);
	    List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty()) {
	    	extent.setSystemInfo("Groups", includedGroups.toString());
	    }
	  }
	
	
	public void onTestSuccess(ITestResult result) {
		
	    test = extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.PASS, result.getName() + "got successfully executed");
	  }
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try{
			String imgPath = new BaseClass().captureScreen(result.getName()); // capturescreen is not directly called because 
			test.addScreenCaptureFromPath(imgPath);                           // its not a static method. So a object is used
		
		}    
		
		// if ss is not properly taken or ss is not available it will still try to attach the ss resulting in
		// File not found exception, so try catch block is used.
		
		catch(Exception e1) {
			e1.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP, result.getName() + "got skipped");
	    test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		
		// The following steps is used to open the reported generated automatically and is optional
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
	  }

}
