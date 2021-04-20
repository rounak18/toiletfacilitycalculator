package govt.mbie.calculator.util.loghelper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean root = false;

	private LoggerHelper() {

	}

	/**
	 * Retrieve a logger instance named according to class name.
	 * 
	 * @param cls The name of <code>cls</code> will be used as the name of the logger to retrieve.
	 * @return Logger instance named according to class name
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "//log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}

}
