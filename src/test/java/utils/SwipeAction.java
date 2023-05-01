package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class SwipeAction {
    private AppiumDriver<MobileElement> appiumDriver;

    public SwipeAction(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void swipeFromLeftToRight() {
        Dimension windownSize = this.appiumDriver.manage().window().getSize();
        int screenHeight = windownSize.getHeight();
        int screenWidth = windownSize.getWidth();
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = xStartPoint;

        int yStartPoint = 80 * screenHeight / 100;
        int yEndPoint = 30 * screenHeight / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        TouchAction touchAction = new TouchAction(this.appiumDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform();

    }

    public void swipeFromLeftToRight(int times) {
        for (int i = 0; i < times; i++) {
            this.swipeFromLeftToRight();
        }

    }
}
