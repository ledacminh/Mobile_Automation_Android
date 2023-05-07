package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import utils.MobileCapabilityTypeEx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static AndroidDriver<MobileElement> androidDriver;

    public static AndroidDriver<MobileElement> getAppiumDriver() {
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

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        return androidDriver;
    }

    public void closeDriver(AndroidDriver<MobileElement> androidDriver) {
        androidDriver.quit();
    }

    public void startRecordingVideo(AndroidDriver<MobileElement> androidDriver) {
        ((CanRecordScreen) androidDriver).startRecordingScreen();
    }

    public void stopRecordingVideo(AndroidDriver<MobileElement> androidDriver) {
        try {
            String video = ((CanRecordScreen) androidDriver).stopRecordingScreen();
            //Save the records video
            byte[] decodeVideo = Base64.getMimeDecoder().decode(video);
            String videoDir = System.getProperty("user.dir") + "/videos";
            Path testVideoDir = Paths.get(videoDir);
            Files.createDirectories(testVideoDir);
            Path testVideoFileLocation = Paths.get(testVideoDir.toString(), String.format("%s-%d.%s", "test", System.currentTimeMillis(), "mp4"));
            Files.write(testVideoFileLocation, decodeVideo);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void takeScreenshots(AndroidDriver<MobileElement> androidDriver, ITestResult iTestResult) {
        if (ITestResult.SUCCESS == iTestResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) androidDriver;
                File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File directory = new File(GlobalConstants.TAKE_SCREENSHOTS_PATH);
                if (!directory.exists()) {
                    boolean isCreated = directory.mkdirs();
                    if (!isCreated) {
                        throw new RuntimeException("[BaseTest][takeScreenshots] Created unsuccessfully");
                    }
                }
                FileHandler.copy(file, new File(GlobalConstants.TAKE_SCREENSHOTS_PATH + iTestResult.getName() + "_" + GlobalConstants.CURRENT_DATE_TIME + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("[BaseTest][takeScreenshots] Exception while taking screenshot " + e.getMessage());
            }
        }
    }

}
