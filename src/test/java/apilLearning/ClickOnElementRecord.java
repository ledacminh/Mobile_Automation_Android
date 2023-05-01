package apilLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BasePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class ClickOnElementRecord {
    public static void main(String[] args) throws InterruptedException, IOException {
        AndroidDriver<MobileElement> androidDriver = BasePage.getAndroidDriver();
        ((CanRecordScreen) androidDriver).startRecordingScreen();
        Thread.sleep(10000);
        MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
        loginLabel.click();

        Thread.sleep(3000);
        //Enter email
        MobileElement email = androidDriver.findElementByAccessibilityId("input-email");
        System.out.println("Email Test is " + email.getText());
        email.sendKeys("ledacminh0305@gmail.com");

        Thread.sleep(6000);
        //Enter passsword
        MobileElement password = androidDriver.findElementByAccessibilityId("input-password");
        password.sendKeys("password");

        Thread.sleep(2000);
        //click button Login
        MobileElement buttonLogin = androidDriver.findElementByAccessibilityId("button-LOGIN");
        buttonLogin.click();

        Thread.sleep(2000);
        MobileElement successMessage = androidDriver.findElementById("android:id/alertTitle");
        System.out.println("Message login successfully " + successMessage.getText());

        File formScreenBase64Data = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        String formScreenPath = System.getProperty("user.dir") + "/screenshot/" + "formScreen.png";
        System.out.println("Path: " + formScreenPath);
        FileUtils.copyFile(formScreenBase64Data, new File(formScreenPath));

        MobileElement buttonOK = androidDriver.findElementById("android:id/button1");
        buttonOK.click();

        MobileElement buttonForm = androidDriver.findElementByXPath("//*[@text='Forms']");
        buttonForm.click();

        Thread.sleep(3000);

        //Scroll up and down
        Dimension windownSize = androidDriver.manage().window().getSize();
        int screenHeight = windownSize.getHeight();
        int screenWidth = windownSize.getWidth();
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = xStartPoint;

        int yStartPoint = 80 * screenHeight / 100;
        int yEndPoint = 30 * screenHeight / 100;

        System.out.println("Height " + screenHeight);
        System.out.println("Width " + screenWidth);


        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform();

        Thread.sleep(2000);
        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(startPoint)
                .release()
                .perform();


        String video = ((CanRecordScreen)androidDriver).stopRecordingScreen();
        //Save the records video\
        byte[] decodevideo = Base64.getMimeDecoder().decode(video);
        String videoDir = System.getProperty("user.dir") + "/videos";
        Path testVideoDir = Paths.get(videoDir);
        Files.createDirectories(testVideoDir);
        Path testVideoFileLocation = Paths.get(testVideoDir.toString(), String.format("%s-%d.%s", "test", System.currentTimeMillis(), "mp4"));
        Files.write(testVideoFileLocation, decodevideo);

    }

    //Record a video
}
