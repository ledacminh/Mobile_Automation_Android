package apilLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BasePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class MutilpleApp {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppiumDriver<MobileElement> appiumDriver = BasePage.getAppiumDriver();
        Thread.sleep(10000);
        MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
        loginLabel.click();

        Thread.sleep(3000);
        //Enter email
        MobileElement email = appiumDriver.findElementByAccessibilityId("input-email");
        System.out.println("Email Test is " + email.getText());
        email.sendKeys("ledacminh0305@gmail.com");

        Thread.sleep(6000);
        //Enter passsword
        MobileElement password = appiumDriver.findElementByAccessibilityId("input-password");
        password.sendKeys("password");

        Thread.sleep(2000);
        //click button Login
        MobileElement buttonLogin = appiumDriver.findElementByAccessibilityId("button-LOGIN");
        buttonLogin.click();

        Thread.sleep(2000);
        MobileElement successMessage = appiumDriver.findElementById("android:id/alertTitle");
        System.out.println("Message login successfully " + successMessage.getText());

        File formScreenBase64Data = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
        String formScreenPath = System.getProperty("user.dir") + "/screenshot/" + "formScreen.png";
        System.out.println("Path: " + formScreenPath);
        FileUtils.copyFile(formScreenBase64Data, new File(formScreenPath));

        MobileElement buttonOK = appiumDriver.findElementById("android:id/button1");
        buttonOK.click();

        MobileElement buttonForm = appiumDriver.findElementByXPath("//*[@text='Forms']");
        buttonForm.click();

        Thread.sleep(3000);

        //Scroll up and down
        Dimension windownSize = appiumDriver.manage().window().getSize();
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
        TouchAction touchAction = new TouchAction(appiumDriver);
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

        appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

        //Try to open Setting App
        appiumDriver.activateApp("com.android.settings");

        //Go to wifi to turn the wifi off and on

        Thread.sleep(5000);
        MobileElement settingWifi = appiumDriver.findElementByXPath("//*[@text='Network & internet']");
        settingWifi.click();

        Thread.sleep(3000);
        MobileElement isWiFi = appiumDriver.findElementByXPath("//android.widget.Switch[@content-desc='Wi‑Fi']");
        isWiFi.click();
        Thread.sleep(3000);
        isWiFi.click();

        //Relaunch the testing app again
        appiumDriver.activateApp("com.wdiodemoapp");
        Thread.sleep(5000);
        MobileElement textInputForms = appiumDriver.findElementByAccessibilityId("text-input");
        textInputForms.sendKeys("admin12345");
        //interact with other elements on the app


        appiumDriver.activateApp("com.android.settings");

        //Go to wifi to turn the wifi off and on

        Thread.sleep(5000);
        MobileElement settingWifiNext = appiumDriver.findElementByXPath("//*[@text='Network & internet']");
        settingWifiNext.click();

        Thread.sleep(3000);
        MobileElement isWiFiNext = appiumDriver.findElementByXPath("//android.widget.Switch[@content-desc='Wi‑Fi']");
        isWiFiNext.click();
        Thread.sleep(3000);
        isWiFiNext.click();
    }
}
