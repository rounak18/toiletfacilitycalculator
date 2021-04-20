package govt.mbie.calculator.util.propertiesmanager;


public class Config {
	
	   private final DefaultPropertyLoader propertyLoader;
	   private final ProxyConfig proxyConfig;

	    private static class ConfigHolder {
	        static final Config INSTANCE = new Config();
	    }

	    public static Config getInstance() {
	        return ConfigHolder.INSTANCE;
	    }

	    /**
	     * Uses DefaultPropertiesLoader to import the config and user properties files.
	     */
	    protected Config() {
	         this(DefaultPropertiesLoader.getInstance());
	    }

	    /**
	     * Uses the supplied PropertiesLoader to import the config and user properties files.
	     *
	     * @param propertiesLoader Configuration loader
	     */
	    protected Config(PropertiesLoader propertiesLoader) {
	        propertyLoader = new DefaultPropertyLoader(propertiesLoader.getProperties());

	        proxyConfig = new ProxyConfig(propertyLoader);
	    }

	    /**
	     * @return Configured environment.
	     */
	    public String getEnvironment() {
	        return propertyLoader.getEnvironment();
	    }

	    /**
	     * @return Configuration for proxy.
	     */
	    public ProxyConfig getProxyConfig() {
	        return proxyConfig;
	    }

	    /**
	     * @return a loader for loading properties across config.properties and user.properties, taking environment into account.
	     */
	    public PropertyLoader getPropertyLoader() {
	        return propertyLoader;
	    }

}
