package govt.mbie.calculator.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import govt.mbie.calculator.pages.calculator.ToiletCalculatorHomePage;
import govt.mbie.calculator.util.extentmanager.ExtentManager;
import govt.mbie.calculator.util.listner.WebEventListener;
import govt.mbie.calculator.util.loghelper.ExtentLogger;
import govt.mbie.calculator.util.loghelper.LoggerHelper;
import govt.mbie.calculator.util.propertiesmanager.AppConfig;
import govt.mbie.calculator.util.propertiesmanager.WebDriverConfig;
import govt.mbie.calculator.util.screenshot.ScreenshotUtility;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExtentTest test;
	public static ExtentReports extent;

	public ToiletCalculatorHomePage toiletCalculatorHomePage;
	
	private static Logger log = LoggerHelper.getLogger(TestBase.class);

	public TestBase() {

	}

	/**
	 * Method executed before the execution of all the test cases defined in the suite. object of
	 * ExtentReports is created for reporting purposes. 
	 * @throws Exception.
	 */
	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent = ExtentManager
				.getInstance();		
	}

	/**
	 * Method run before any test method belonging to the classes inside the test tag is run.
	 * ExtentTest Class instance created for test reporting purpose. proxyInitialization done.
	 * @param method provide information about method under test.
	 */
	@BeforeMethod
	public void setUp(Method method) {


		ExtentLogger
		.setTest(extent.createTest(method.getName()));

		ExtentLogger.getTest().log(Status.INFO," ************** " + method.getName() + " Test Started ***************");

		log.info("**************" + method.getName() + "Started ***************");
		initialization();

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(WebDriverConfig.getInstance().getPageLoadTimeout(),
				TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(WebDriverConfig.getInstance().getimplicitWaitTimeout(),
				TimeUnit.SECONDS);

		driver.get(AppConfig.getInstance().getUrl());

		driver.manage().deleteAllCookies();		
		
		ExtentLogger.getTest().log(Status.INFO, "Web Url Opened");
	
		log.info("Web Url Opened");
		
	}

	/**
	 * Method run after each test method belonging to the classes inside the test tag is run.
	 * @param result used to update test result based on status.
     * @throws IOException.
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			
			ExtentLogger.getTest().log(Status.FAIL," ************** " + result.getThrowable() + " Test is Failed ************** ");
			String imagePath = ScreenshotUtility.captureScreen(result.getName(), driver);
			ExtentLogger.getTest().addScreenCaptureFromPath(imagePath);
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			ExtentLogger.getTest().log(Status.PASS," ************** " + result.getName() + " Test is Passed ************** ");
			String imagePath = ScreenshotUtility.captureScreen(result.getName(), driver);
			ExtentLogger.getTest().addScreenCaptureFromPath(imagePath);
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			
			ExtentLogger.getTest().log(Status.SKIP," ************** " + result.getThrowable() + " Test is Skipped ************** ");
		}
		
		ExtentLogger.getTest().log(Status.INFO," **************\" + result.getName() + \" Test Finished *************** ");
		log.info("**************" + result.getName() + " Test Finished ***************");

	}
	
	/**
	 * Method run after all test method belonging to the classes inside the test tag is run.
	 * Tear down Webdriver instance.
	 * ExtentReports Class instance flushed to generate report for test.
     * @throws IOException.
	 */		
	@AfterClass
	public void teardown() throws IOException {

		driver.quit();
		driver = null;
		extent.flush();

	}

	/**
	 * Method to add log into extent report.
	 * @param log message to be added.
	 */		
	public static void logExtentReport(String log) {
		ExtentLogger.getTest().log(Status.INFO, log);
	}
	
	/**	 
	 * Method to Webdriver Initialsation.
	 */	
	private static void initialization() {

		ExtentLogger.getTest().log(Status.INFO,"Web Driver initialization Started");
		log.info("Web Driver initialization Started");

		driver = DriverFactory.createInstance(WebDriverConfig.getInstance().getBrowserProvider(),
				WebDriverConfig.getInstance().getOsplatform());

		DriverManager.setWebDriver(driver);

		e_driver = new EventFiringWebDriver(DriverManager.getDriver());

		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver

		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		ExtentLogger.getTest().log(Status.INFO,"Web Driver initialization Completed");
		log.info("Web Driver initialization Completed");

	}

}
