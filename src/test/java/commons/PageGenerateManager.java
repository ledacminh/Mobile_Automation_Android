package commons;

import actions.FormsPage;
import actions.HomePage;
import actions.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PageGenerateManager {

    public static HomePage getHomePage(AndroidDriver<MobileElement> androidDriver) {
        return new HomePage(androidDriver);

    }

    public static LoginPage getLoginPage(AndroidDriver<MobileElement> androidDriver) {
        return new LoginPage(androidDriver);
    }

    public static FormsPage getFormsPage(AndroidDriver<MobileElement> androidDriver) {
        return new FormsPage(androidDriver);
    }

}
