package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    private static final ThreadLocal<AndroidDriver<MobileElement>> androidDriver = new ThreadLocal<>();

    public static AndroidDriver<MobileElement> getAppiumDriver(String url) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, GlobalConstants.TIME_OUT);

            URL androidServer = new URL(url);
            androidDriver.set(new AndroidDriver<>(androidServer, desiredCapabilities));
            androidDriver.get().manage().timeouts().implicitlyWait(GlobalConstants.TIME_OUT, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return androidDriver.get();
    }

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        return androidDriver.get();
    }

    public void closeDriver(AndroidDriver<MobileElement> androidDriver) {
        androidDriver.quit();
    }

    public void startRecordingVideo(AndroidDriver<MobileElement> androidDriver) {
        ((CanRecordScreen) androidDriver).startRecordingScreen();
    }

    public void stopRecordingVideo(AndroidDriver<MobileElement> androidDriver, ITestResult iTestResult) {
        try {
            String video = ((CanRecordScreen) androidDriver).stopRecordingScreen();
            //Save the records video
            byte[] decodeVideo = Base64.getMimeDecoder().decode(video);
            String videoDir = GlobalConstants.PROJECT_PATH + "/videos";
            Path testVideoDir = Paths.get(videoDir);
            Files.createDirectories(testVideoDir);
            //Path testVideoFileLocation = Paths.get(testVideoDir.toString(), String.format("%s-%d.%s", "Test", System.currentTimeMillis(), "mp4"));
            Path testVideoFileLocation = Paths.get(testVideoDir.toString(), iTestResult.getName() + "_" + GlobalConstants.CURRENT_DATE_TIME + ".mp4");
            Files.write(testVideoFileLocation, decodeVideo);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void takeScreenshots(ITestResult iTestResult) {
        try {
            File formScreenBase64Data = ((TakesScreenshot) androidDriver.get()).getScreenshotAs(OutputType.FILE);
            String formScreenPath = GlobalConstants.PROJECT_PATH + "/screenshots/" + iTestResult.getName() + "_" + GlobalConstants.CURRENT_DATE_TIME + ".png";
            System.out.println("Path: " + formScreenPath);
            FileUtils.copyFile(formScreenBase64Data, new File(formScreenPath));
        } catch (IOException e) {
            throw new RuntimeException("[BaseTest][takeScreenshots] Exception while taking screenshot " + e.getMessage());
        }
    }

}
