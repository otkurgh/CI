package Base;


import java.io.File;
import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;
	

	import io.github.bonigarcia.wdm.WebDriverManager;

	@SuppressWarnings("deprecation")
	public class BaseClass {

		public static WebDriver driver;
		public static ExtentHtmlReporter htmlreporter;
		public static ExtentReports extent;
		public static ExtentTest test;
		public static String browser;


		@Parameters({"browsertype"})
		@BeforeClass
		public void beforeClass(@Optional("chrome") String browsertype) {
            	
			browser = browsertype;
			htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtendReport/myReport.html");
			htmlreporter.config().setDocumentTitle("Automation Test Report");
			htmlreporter.config().setReportName("Smoke Test");
			htmlreporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlreporter);

		}

	@AfterClass
	public void afterAllTestCompleted(){

		extent.flush();
		if(driver!=null){
			
			driver.quit();
		}
		
	}
		
	@BeforeMethod
	public void beforeAllMethod(){
		
		
		if(browser.contains("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
		} if(browser.contains("ff")){
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();		
		}	
		
	}
	

	@AfterMethod
	public void afterAllMethodDone(ITestResult result){
		
		try{
			
			if(result.getStatus()==ITestResult.FAILURE){
				
		    test.log(Status.FAIL, result.getName());	
			 test.log(Status.FAIL, result.getThrowable());
			 
			 TakesScreenshot screenshot=(TakesScreenshot)driver;
			 File file=screenshot.getScreenshotAs(OutputType.FILE);
			 File destfile=new File("C:/Users/nawar/LW/ContinuousIntegration/target/screenShot/scrsht.png");
			 FileUtils.copyFile(file, destfile);
			}
			 
			 else if(result.getStatus()==ITestResult.SKIP){
				 
				 test.log(Status.SKIP, result.getName());
				
				 
			 }else if(result.getStatus()==ITestResult.SUCCESS){
				 
				 test.log(Status.PASS, result.getName());
				
			 }
				
				
			}catch(Exception e){
			e.getStackTrace();
			
		}
		
			
			
			
		}
		
		
}
