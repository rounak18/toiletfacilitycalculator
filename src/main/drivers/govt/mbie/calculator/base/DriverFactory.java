package govt.mbie.calculator.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import govt.mbie.calculator.util.propertiesmanager.Config;
import govt.mbie.calculator.util.propertiesmanager.ProxyConfig;

public class DriverFactory {

	private static WebDriver driver = null;
	private static ProxyConfig proxyConfig;

	/**
	 * Method to Create Webdriver instance as per os platform.
	 * Not Fully implemented, implemented completely only for FF and Windows.
	 * @param browserName name of the browser for test.
	 * @param osPlaform name of os platform used for test.
	 * @return WebDriver instance.
	 */
	static WebDriver createInstance(String browserName, String osPlaform) {

		if (osPlaform.equalsIgnoreCase("linux")) {

			driver = createLinuxInstace(browserName);

			return driver;
		}
		if (osPlaform.equalsIgnoreCase("windows")) {

			driver = createWindowsInstace(browserName);

			return driver;		}

		return driver;
	}

	/**
	 * Method to Create linux instace of driver based on broswer.
	 * Not Fully implemented.
	 * @param browserName name of the browser for test.
	 * @return WebDriver instance.
	 */
	private static WebDriver createLinuxInstace(String browserName) {

		if (browserName.equalsIgnoreCase("firefox")) {

			proxyConfig = Config.getInstance().getProxyConfig();

			System.setProperty("webdriver.gecko.driver", "drivers//linuxdriver//geckodriver");

			FirefoxOptions options = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();

			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.http", proxyConfig.getProxyHost());
			profile.setPreference("network.proxy.http_port", proxyConfig.getProxyPort());
			profile.setPreference("network.proxy.ssl", proxyConfig.getProxyHost());
			profile.setPreference("network.proxy.ssl_port", proxyConfig.getProxyPort());
			profile.setPreference("network.proxy.ssl_port", proxyConfig.getProxyPort());

			options.setProfile(profile);
			options.setHeadless(true);

			driver = new FirefoxDriver(options);

			return driver;
		}
		if (browserName.toLowerCase().contains("chrome")) {

			System.setProperty("webdriver.chrome.driver", "drivers//linuxdriver//chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(true);
			driver = new ChromeDriver();

			return driver;
		}

		return driver;

	}

	/**
	 * Method to create windows instance of driver based on broswer.
	 * Not Fully implemented, implemented completely only for FF and Windows.
	 * @param browserName name of the browser for test.
	 * @return WebDriver instance.
	 */
	private static WebDriver createWindowsInstace(String browserName) {

		if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "drivers//windriver//geckodriver.exe");

			FirefoxOptions options = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();

			profile.setPreference("network.proxy.type", 4);
			profile.setPreference("print.always_print_silent", true);
			profile.setPreference("print_printer", "Microsoft Print to PDF");
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.dir", "target//");
			profile.setPreference("pdfjs.disabled", true);

			options.setProfile(profile);
			driver = new FirefoxDriver(options);

			return driver;
		}
		if (browserName.toLowerCase().contains("chrome")) {

			System.setProperty("webdriver.chrome.driver", "drivers//windriver//chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(true);
			driver = new ChromeDriver();

			return driver;
		}
		if (browserName.toLowerCase().contains("ie")) {

			System.setProperty("webdriver.ie.driver", "drivers//windriver//IEDriverServer.exe");
			driver = new InternetExplorerDriver();

			return driver;
		}

		return driver;

	}

}
