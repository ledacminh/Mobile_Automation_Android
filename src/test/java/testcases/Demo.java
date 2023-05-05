package testcases;

import commons.BasePage;
import commons.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class Demo extends BasePage {
    private AndroidDriver<MobileElement> androidDriver;

    @Test
    public void test_o1() throws InterruptedException, IOException {
        androidDriver = BaseTest.getAppiumDriver();
        //Thread.sleep(10000);
        clickToElement(androidDriver, "//android.widget.Button[@content-desc=\"Login\"]/android.widget.TextView[1]");

        //Thread.sleep(3000);
        enterTextToElement(androidDriver, "//android.widget.EditText[@content-desc=\"input-email\"]", "ledacminh0305@gmail.com");

        //Thread.sleep(6000);
        enterTextToElement(androidDriver, "//android.widget.EditText[@content-desc=\"input-password\"]", "password");

        //Thread.sleep(2000);
        clickToElement(androidDriver, "//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]/android.view.ViewGroup");

    }

}
