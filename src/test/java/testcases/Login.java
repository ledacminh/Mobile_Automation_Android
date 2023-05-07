package testcases;

import actions.HomePage;
import actions.LoginPage;
import commons.BaseTest;
import commons.Log;
import commons.PageGenerateManager;
import extentreport.ReportListener;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ReportListener.class})
public class Login extends BaseTest {
    private AndroidDriver<MobileElement> androidDriver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void init() {
        androidDriver = getAppiumDriver();
        homePage = PageGenerateManager.getHomePage(androidDriver);
        homePage.clickOnLoginButton();
        loginPage = PageGenerateManager.getLoginPage(androidDriver);
        Log.info("Starting......!");
    }

    @Test
    public void test_o1() {
        Log.info("Enter an email to Email field");
        loginPage.enterValueToEmail("ledacminh0305@gmail.com");
        Log.info("Enter a password to Password field");
        loginPage.enterValueToPassword("0966152432");
        Log.info("Click on Login button");
        loginPage.clickOnButtonLogin();
    }

    @AfterMethod
    public void afterMethod() {
        androidDriver.quit();
    }

}
