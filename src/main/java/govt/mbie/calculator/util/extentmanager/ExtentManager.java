package govt.mbie.calculator.util.extentmanager;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import govt.mbie.calculator.util.propertiesmanager.Config;

public class ExtentManager {

	private static ExtentReports extent;
	
	private ExtentManager() {

	}

	/**
	 * Get the Instance of ExtentReports.
	 * 
	 * @return object of ExtentReports configured to genrate report
	 */
	public static ExtentReports getInstance() {
		if (extent == null) {
			String location = System.getProperty("user.dir") + "//target//" + "MBIE_Toilet_Facility_Calculator_Test_Automation_Report_" +Config.getInstance().getEnvironment()+ ".html";
			return createInstance(location);
		} else {
			return extent;
		}
	}

	/**
	 * Create the Instance of ExtentReports. *
	 * 
	 * @param fileName Name of file where result is Stored
	 * @return object of ExtentReports configured to genrate report
	 */
	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "//extentconfig.xml"));
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

}
