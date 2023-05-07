package actions;

import commons.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ui.HomePageUI;

public class HomePage extends BasePage {
    private AndroidDriver<MobileElement> androidDriver;

    public HomePage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void clickOnLoginButton() {
        clickToElement(androidDriver, HomePageUI.LOGIN);
    }

    public void clickOnFormButton() {
        clickToElement(androidDriver, HomePageUI.FORMS);
    }
}
