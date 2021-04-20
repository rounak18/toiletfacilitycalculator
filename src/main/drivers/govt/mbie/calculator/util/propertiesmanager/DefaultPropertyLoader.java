package govt.mbie.calculator.util.propertiesmanager;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultPropertyLoader implements PropertyLoader {
	
    private final Properties properties;
    private final String environment;
    
    public DefaultPropertyLoader(Properties properties) {
        this.properties = properties;
        this.environment = loadEnvironmentProperty();
    }
    
    private String loadEnvironmentProperty() {
        // Try environment variable first
        String environment = System.getProperty("environment", "");

        if (environment.isEmpty()) {
            environment = properties.getProperty("environment", "");
        }

        return environment;
    }
    
    public String getEnvironment() {
        return environment;
    }


	@Override
	public String getProperty(String key) {
		
        String value = retrieveProperty(key);

        if (value.isEmpty()) {
            throw new IllegalArgumentException(String.format("Unable to find property %s", key));
        }

        return value;
	}

	@Override
	public String getProperty(String key, String defaultValue) {
        String value = retrieveProperty(key);

        if (value.isEmpty()) {
            value = defaultValue == null ? "" : defaultValue;
        }

        return value;
	}

	@Override
	public boolean getPropertyAsBoolean(String key, String defaultValue) {
        String value = retrieveProperty(key);

        if (value.isEmpty()) {
            value = defaultValue == null ? "false" : defaultValue;
        }

        return Boolean.valueOf(value);
	}

	@Override
	public int getPropertyAsInteger(String key, String defaultValue) {
        String value = retrieveProperty(key);

        if (value.isEmpty()) {
            value = defaultValue == null ? "0" : defaultValue;
        }

        return Integer.valueOf(value);
	}

	@Override
	public Map<String, String> getPropertiesStartingWith(String keyPrefix) {
        return getPropertiesStartingWith(keyPrefix, false);

	}

	@Override
	public Map<String, String> getPropertiesStartingWith(String keyPrefix, boolean trimPrefix) {
	      Map<String, String> result = new HashMap<>();

	        @SuppressWarnings("unchecked")
			Enumeration<String> en = (Enumeration<String>) properties.propertyNames();
	        while (en.hasMoreElements()) {
	            String propName = en.nextElement();
	            String propValue = properties.getProperty(propName);

	            if (propName.startsWith(keyPrefix)) {
	                if (trimPrefix) {
	                    propName = propName.substring(keyPrefix.length());
	                }

	                result.put(propName, propValue);
	            }
	        }
	        
	        return result;
	}
	
    private String retrieveProperty(String key) {
        String value = null;

        // Attempt to get setting for environment
        if (environment != null && !environment.isEmpty()) {
            value = properties.getProperty(environment + "." + key);
        }

        // Attempt to get default setting
        if (value == null) {
            value = properties.getProperty(key);
        }

        if (value == null) {
            value = "";
        } else {
            value = value.trim();
        }

        return value;
    }

}
