package actions;

import commons.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ui.FormsUI;

public class FormsPage extends BasePage {
    private AndroidDriver<MobileElement> androidDriver;

    public FormsPage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void scrollFromTopToBottom(int startPercentage, int endPercentage, int anchorPercentage) {
        waitForElementIsVisible(androidDriver, FormsUI.INPUT_FIELD);
        verticalSwipeByPercentages(androidDriver, startPercentage, endPercentage, anchorPercentage);
    }


}
