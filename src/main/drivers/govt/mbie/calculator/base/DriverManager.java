package govt.mbie.calculator.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**	 
	 * Method to return Webdriver Instance.
	 */	
	public static WebDriver getDriver() {
		return webDriver.get();
	}

	/**	 
	 * Method to set Webdriver Instance.
	 * @param driver object to set for thread.
	 */	
	static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
		}

}
