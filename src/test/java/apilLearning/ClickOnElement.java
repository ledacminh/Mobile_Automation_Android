package apilLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.BasePage;

public class ClickOnElement {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> appiumDriver = BasePage.getAppiumDriver();
        Thread.sleep(10000);
        MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
        loginLabel.click();

        Thread.sleep(3000);
        //Enter email
        MobileElement email = appiumDriver.findElementByAccessibilityId("input-email");
        email.sendKeys("ledacminh0305@gmail.com");

        Thread.sleep(10000);
        //Enter passsword
        MobileElement password = appiumDriver.findElementByAccessibilityId("input-password");
        password.sendKeys("password");

        Thread.sleep(3000);
        //click button Login
        MobileElement buttonLogin = appiumDriver.findElementByAccessibilityId("button-LOGIN");
        buttonLogin.click();

        Thread.sleep(5000);
        //click button Login
        MobileElement buttonOK = appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button");
        buttonLogin.click();
    }
}
