package testcases;

import actions.HomePage;
import actions.LoginPage;
import commons.BaseTest;
import commons.Log;
import commons.PageGenerateManager;
import commons.TestListener;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class Login extends BaseTest {
    private AndroidDriver<MobileElement> androidDriver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("url")
    @BeforeMethod
    public void init(String url) {
        androidDriver = getAppiumDriver(url);
        homePage = PageGenerateManager.getHomePage(androidDriver);
        homePage.clickOnLoginButton();
        loginPage = PageGenerateManager.getLoginPage(androidDriver);
        Log.info("Starting......!");
    }

    @Test
    public void test_01() {
        Log.info("Enter an email to Email field");
        loginPage.enterValueToEmail("ledacminh0305@gmail.com");
        Log.info("Enter a password to Password field");
        loginPage.enterValueToPassword("0966152432");
        Log.info("Click on Login button");
        loginPage.clickOnButtonLogin();
    }

    @AfterMethod
    public void afterMethod() {
        closeDriver(androidDriver);
    }

}
