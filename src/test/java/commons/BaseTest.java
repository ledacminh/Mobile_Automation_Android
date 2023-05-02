package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.MobileCapabilityTypeEx;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private AndroidDriver<MobileElement> androidDriver;

    public AndroidDriver<MobileElement> getAppiumDriver() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, GlobalConstants.TIME_OUT);

            //Set up the appium server url to connect
            //URL androidServer = new URL(url);
            URL androidServer = new URL("http://localhost:4723/wd/hub");
            androidDriver = new AndroidDriver<>(androidServer, desiredCapabilities);
            androidDriver.manage().timeouts().implicitlyWait(GlobalConstants.TIME_OUT, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return androidDriver;
    }

}
