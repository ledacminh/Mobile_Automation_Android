import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Launch {
    public static void main(String[] args) {

        try {
            //Driver instance
            AppiumDriver<MobileElement> appiumDriver;
            //Setup setCapability
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
            desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

            //setup the applium server url to connect
            URL appiumServer = new URL("http://localhost:4723/wd/hub");

            appiumDriver = new AppiumDriver<>(appiumServer, desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
