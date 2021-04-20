package govt.mbie.calculator.util.propertiesmanager;

import java.util.Properties;

public class WebDriverConfig {

	private final PropertyLoader propertyLoader;

	// Browser
	private String browserProvider;
	private String browserExe;
	private String osplatform;
	private int explicitWaitTimeout;
	private int pageLoadTimeout;
	private int implicitWaitTimeout;

	private static class WDCHolder {
		static final WebDriverConfig INSTANCE = new WebDriverConfig();
	}

	/**
	 * @return singleton instance
	 */
	public static WebDriverConfig getInstance() {
		return WDCHolder.INSTANCE;
	}

	/**
	 * Uses the default Config class's property loader to import the config and user
	 * properties files.
	 */
	protected WebDriverConfig() {
		propertyLoader = Config.getInstance().getPropertyLoader();
		loadProperties();
	}

	/**
	 * Uses the supplied PropertiesLoader to import the config and user properties
	 * files.
	 *
	 * @param propertiesLoader Configuration loader
	 */
	protected WebDriverConfig(PropertiesLoader propertiesLoader) {
		this(propertiesLoader.getProperties());
	}

	/**
	 * Allow injection of properties for testing purposes.
	 *
	 * @param properties Default properties
	 */
	protected WebDriverConfig(Properties properties) {
		propertyLoader = new DefaultPropertyLoader(properties);
		loadProperties();
	}

	/**
	 * @return a loader for loading properties across config.properties and
	 *         user.properties, taking environment into account.
	 */
	public PropertyLoader getPropertyLoader() {
		return propertyLoader;
	}

	private void loadProperties() {

		browserProvider = propertyLoader.getProperty("webdriver.browserProvider", "firefox");
		browserExe = propertyLoader.getProperty(getBrowserProvider().toLowerCase() + ".exe", null);
		osplatform = propertyLoader.getProperty("webdriver.osplatform");
		explicitWaitTimeout = propertyLoader.getPropertyAsInteger("explicitwait.timeout", "10");
		pageLoadTimeout = propertyLoader.getPropertyAsInteger("pageload.timeout", "10");
		implicitWaitTimeout = propertyLoader.getPropertyAsInteger("implicitwait.timeout", "10");
	}

	public String getBrowserProvider() {
		return browserProvider;
	}

	public String getBrowserExePath() {
		return browserExe;
	}

	public String getOsplatform() {
		return osplatform;
	}

	public int getExplicitWaitTimeOut() {
		return explicitWaitTimeout;
	}

	public int getimplicitWaitTimeout() {
		return implicitWaitTimeout;
	}

	public int getPageLoadTimeout() {
		return pageLoadTimeout;
	}

}
