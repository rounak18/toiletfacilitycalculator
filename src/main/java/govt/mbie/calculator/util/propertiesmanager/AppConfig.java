package govt.mbie.calculator.util.propertiesmanager;

import org.apache.log4j.Logger;

import govt.mbie.calculator.util.loghelper.LoggerHelper;

public class AppConfig {

	private static Logger log = LoggerHelper.getLogger(AppConfig.class);

	private final PropertyLoader propertyLoader;

	private String url;
	private String testdata;

	private static class Holder {
		static final AppConfig INSTANCE = new AppConfig();
	}

	public static AppConfig getInstance() {
		return Holder.INSTANCE;
	}

	private AppConfig() {
		propertyLoader = Config.getInstance().getPropertyLoader();
		loadProperties();
	}

	public void logSettings() {
		log.info("Environment:        " + Config.getInstance().getEnvironment());
		log.info("url:                " + url);
	}

	private void loadProperties() {
		url = propertyLoader.getProperty("url");
		testdata = propertyLoader.getProperty("testdata");
	}

	// Application properties

	public String getUrl() {
		return url;
	}

	public String getTestData() {
		return testdata;
	}

}
