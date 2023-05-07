package commons;

import commons.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class readPropertyFile {
    private static final Map<String, String> mapDataProperties = new HashMap<String, String>();

    static {
        try {
            String env = System.getProperty("env") + "";
            String pathFileEnvConfig = "";
            switch (env) {
                case "testing":
                    pathFileEnvConfig = GlobalConstants.TESTING_PROPERTY_PATH;
                    break;
                case "production":
                    pathFileEnvConfig = GlobalConstants.PRODUCTION_PROPERTY_PATH;
                    break;
                case "staging":
                    pathFileEnvConfig = GlobalConstants.STAGING_PROPERTY_PATH;
                    break;
                default:
                    pathFileEnvConfig = GlobalConstants.DEV_PROPERTY_PATH;
                    break;
            }
            Properties properties = new Properties();
            properties.load(new FileInputStream(pathFileEnvConfig));
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                mapDataProperties.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("[Hooks] " + e.getMessage());
        }
    }

    public static String getString(String key) {
        String value = "";
        try {
            value = mapDataProperties.get(key);
        } catch (Exception e) {
            throw new RuntimeException("[Hooks].[getString]" + e.getMessage());
        }
        return value;
    }
}
