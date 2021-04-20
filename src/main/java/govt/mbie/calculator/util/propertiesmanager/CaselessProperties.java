package govt.mbie.calculator.util.propertiesmanager;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class CaselessProperties extends Properties {
    private static final long serialVersionUID = 4112578634029874840L;

    Map<String, String> lookup = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

    @Override
    public synchronized Object put(Object key, Object value) {
        lookup.put(((String) key), (String) key);

        return super.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        String realKey = lookup.get(key);

        return super.getProperty(realKey == null ? key : realKey);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String realKey = lookup.get(key);

        return super.getProperty(realKey == null ? key : realKey, defaultValue);
    }

}
