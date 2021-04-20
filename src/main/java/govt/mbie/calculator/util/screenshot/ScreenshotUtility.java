package govt.mbie.calculator.util.screenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;


import govt.mbie.calculator.base.TestBase;
import govt.mbie.calculator.util.loghelper.ExtentLogger;
import govt.mbie.calculator.util.loghelper.LoggerHelper;

public class ScreenshotUtility extends TestBase {

	private static Logger log = LoggerHelper.getLogger(TestBase.class);
	
	private ScreenshotUtility() {

	}

	/**
	 * Method to take screenshot.
	 * @param fileName is location of the file to store screenshot.
	 * @param driver object of running test.
	 */
	public static String captureScreen(String fileName, WebDriver driver) {
		
		if (driver == null) {
			log.info("driver is null..");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}

		Reporter.log("captureScreen method called");
		File destFile = null;
		String base64 = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		

		try {

			destFile = new File(System.getProperty("user.dir") + "\\screenshots\\" + "/" + fileName + "_"
					+ formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(screFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
			InputStream is = new FileInputStream(destFile);
			byte[] imageBytes = IOUtils.toByteArray(is);

			base64 = new String(Base64.encodeBase64String(imageBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + base64;

	}

	/**
	 * Method to add page screenshot in extent report.
	 * @param driver object of running test.
	 */
	public static void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		String screen = captureScreen("", driver);
		try {

			ExtentLogger.getTest().addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to highlight and capture screenshots and click.
	 * @param obj webelement object to capture screenshot and click.
	 */
	public static void captureandclick(Object obj) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		/* Highlight the element */
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", obj, " border: 3.5px dashed red;");
		// Take Screenshot
		ScreenshotUtility.getNavigationScreen(driver);
		// set back to original state
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", obj, "");
		((WebElement) obj).click();

	}

	/**
	 * Method to highlight and capture screenshots.
	 * @param obj webelement object to capture screenshot.
	 */
	public static void capture(Object obj) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		/* Highlight the element */
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", obj, " border: 3.5px dashed red;");
		// Take Screenshot
		ScreenshotUtility.getNavigationScreen(driver);
		// set back to original state
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", obj, ""); 

	}

}
