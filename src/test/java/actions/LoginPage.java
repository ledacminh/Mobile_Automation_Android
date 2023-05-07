package actions;

import commons.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ui.LoginUI;

public class LoginPage extends BasePage {
    private AndroidDriver<MobileElement> androidDriver;
    public LoginPage(AndroidDriver<MobileElement> androidDriver){
        this.androidDriver = androidDriver;
    }

    public void enterValueToEmail(String email){
        enterTextToElement(androidDriver, LoginUI.EMAIL, email);
    }

    public void enterValueToPassword(String password){
        enterTextToElement(androidDriver, LoginUI.PASSWORD, password);
    }

    public void clickOnButtonLogin(){
        clickToElement(androidDriver, LoginUI.BUTTON_LOGIN);
    }
}
