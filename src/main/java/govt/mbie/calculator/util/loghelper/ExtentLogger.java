package govt.mbie.calculator.util.loghelper;

import com.aventstack.extentreports.ExtentTest;

public class ExtentLogger {

	private static ExtentTest test;

	private ExtentLogger() {
	}

	public static void setTest(ExtentTest test) {

		ExtentLogger.test = test;

	}

	public static ExtentTest getTest() {
		return test;
	}

}
