package Operation;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tf.excel.Readexcelfile;
import com.tf.excel.Readsheet_task;

import config.Readconfigfile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{
	public static RemoteWebDriver driver=null;
	public static ExtentReports extent;
	public static ExtentTest extent_test;
	public static int testfailcontainer= 0;
	public static String sheet_name;
	
		
	@BeforeTest
	public void startreport()
	{
		//-----------Define path for extent report and it is override as per boolean flag mentioned true is override
		extent = new ExtentReports(System.getProperty("user.dir")+"\\"+"advancereport.html",true); 
		System.out.println("Extent Report Started");	
		
	}
	@Test(dataProvider="readtestsuite",dataProviderClass=Readexcelfile.class)
	public static void readtestsuiteandexecutetestcase(Object testcase_Name,Object checkflag,Object Suite) throws IOException
	{	Readconfigfile data3= new Readconfigfile();
		System.out.println("Read complete suite");
		String test_Name=testcase_Name.toString();
		String Flag=checkflag.toString();
		String Suitename = Suite.toString();
		String suitename1= System.getProperty("Suite_Name")!=null? System.getProperty("Suite_Name") : data3.getSuiteName();
		if(test_Name!= null && Flag.contains("Y") && Suitename.contains(suitename1))		{
			System.out.println("Execute the test case "+test_Name);
			//--------extent report is start ----------------------------
			extent_test=extent.startTest(test_Name);
			//-----------------------------------------------------------
			
			Readsheet_task.readtask_assheet(test_Name,null,null,null,null,null,null);			
			
		}
		if(test_Name!= null && Flag.contains("N"))
		{
			System.out.println("Skipped the Test case for execution:"+test_Name);
		}
		
	}
	
	@AfterMethod
	public void takeFailTestCaseScrrenShot(ITestResult result) throws IOException
	{
		System.out.println("exectue fail screenshot method");
		
		if(ITestResult.FAILURE == result.getStatus())
		{	System.out.println("start taking screenshot for faild test case");
			testfailcontainer = testfailcontainer+1;
			sheet_name = Readsheet_task.testcase_name_for_fail_scenario;
			Screenshots.capturescreenshot(driver, sheet_name);
			extent_test.log(LogStatus.FAIL, "Test case is Failed");
			extent.endTest(extent_test);
			//extent.flush();
			//driver.quit();			
		}
	}
	@AfterSuite
	public void suiteendactivity()
	{
		extent.flush();
		extent.endTest(extent_test);
		driver.quit();
		System.out.println("Test Execution Finished");
	}
	
	public static void logstart(String operation, String objectname)
	{
		extent_test.log(LogStatus.INFO,"Starting"+operation +"Operation on Object :" +objectname);
	}
	
	public static void loginforpass(String str)
	{
		extent_test.log(LogStatus.PASS, str);
	}
	public static void loginforFail(String str)
	{
		extent_test.log(LogStatus.FATAL, str);
	}
	
	public static void logEnd(String operation, String objectname)
	{
		extent_test.log(LogStatus.INFO,"Operation "+operation +"Sucessfully completed for :" +objectname);
	}
	public static void logendformessage(String message, String Object_Name)
	{
		extent_test.log(LogStatus.INFO, message,Object_Name);
	}
	public static void lunchbrowser()
	{
		Readconfigfile data= new Readconfigfile();
		String browsername= System.getProperty("browser")!=null? System.getProperty("browser") :data.getbrowsername();
		if(browsername.contentEquals("chrome"))
		{
			System.out.println("Start with chrome browser");
		// Launch chrome browser
				// steps for auto update chrome driver
				WebDriverManager.chromedriver().setup(); // step for auto update chrome driver
				System.out.println("Chrome Driver updated sucessfully");
				driver= new ChromeDriver();
				driver.manage().window().maximize();
		}
		if(browsername.contentEquals("Edge"))
		{
		// Launch chrome browser
				// steps for auto update chrome driver
				WebDriverManager.edgedriver().setup(); // step for auto update chrome driver
				System.out.println("Edge Driver updated sucessfully");
				driver= new EdgeDriver();
				driver.manage().window().maximize();
				}
		
	}
	
}

