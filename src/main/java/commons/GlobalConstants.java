package commons;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final long SHORT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 20;
    public static final long RETRY_COUNT = 2;
    public static final String CURRENT_DATE_TIME = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(Calendar.getInstance().getTime());
    public static final String SLASH = File.separator;
    public static final long TIME_OUT = 15;
    public static final int WAIT_ACTION = 2;
    public static final String TAKE_SCREENSHOTS_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "screenshots" + SLASH;
    public static final String DEV_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "main" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "dev.properties";
    public static final String TESTING_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "main" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "testing.properties";
    public static final String STAGING_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "main" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "staging.properties";
    public static final String PRODUCTION_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "main" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "production.properties";


}
