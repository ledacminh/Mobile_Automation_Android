package testcases;

import actions.FormsPage;
import actions.HomePage;
import commons.BaseTest;
import commons.Log;
import commons.PageGenerateManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Forms extends BaseTest {
    private HomePage homePage;
    private FormsPage formsPage;
    private AndroidDriver<MobileElement>androidDriver;

    @BeforeMethod
    public void init() {
        androidDriver = getAppiumDriver();
        homePage = PageGenerateManager.getHomePage(androidDriver);
        homePage.clickOnFormButton();
        formsPage = PageGenerateManager.getFormsPage(androidDriver);
        Log.info("Starting......!");
    }

    @Test
    public void test_01(){
        formsPage.scrollFromTopToBottom(80, 20, 40);
    }

    @Test
    public void test_02(){
        formsPage.scrollFromTopToBottom(20, 80, 40);
    }
}
