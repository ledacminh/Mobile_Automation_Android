package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private WebDriverWait explicit;
    private AndroidDriver<MobileElement> androidDriver;
    private Actions actions;

    public void waitForElementIsVisible(AndroidDriver<MobileElement> androidDriver, String locator) {
        explicit = new WebDriverWait(androidDriver, GlobalConstants.TIME_OUT);
        explicit.until(ExpectedConditions.visibilityOfElementLocated(getXpath(locator)));
    }

    public void waitForElementIsVisible(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        explicit = new WebDriverWait(androidDriver, GlobalConstants.TIME_OUT);
        explicit.until(ExpectedConditions.visibilityOfElementLocated(getDynamicXpath(locator, params)));
    }


    public void waitForElementClickable(AndroidDriver<MobileElement> androidDriver, String locator) {
        waitForElementIsVisible(androidDriver, locator);
        explicit = new WebDriverWait(androidDriver, GlobalConstants.TIME_OUT);
        explicit.until(ExpectedConditions.elementToBeClickable(getXpath(locator)));
    }

    public void waitForElementClickable(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        waitForElementIsVisible(androidDriver, locator, params);
        explicit = new WebDriverWait(androidDriver, GlobalConstants.TIME_OUT);
        explicit.until(ExpectedConditions.elementToBeClickable(getDynamicXpath(locator, params)));
    }

    public void enterTextToElement(AndroidDriver<MobileElement> androidDriver, String locator, String value) {
        waitForElementIsVisible(androidDriver, locator);
        getElement(androidDriver, locator).clear();
        getElement(androidDriver, locator).sendKeys(value);
    }

    public void enterTextToElement(AndroidDriver<MobileElement> androidDriver, String locator, String value, String... params) {
        waitForElementIsVisible(androidDriver, locator, params);
        getDynamicElement(androidDriver, locator, params).clear();
        getDynamicElement(androidDriver, locator, params).sendKeys(value);
    }

    public MobileElement getElement(AndroidDriver<MobileElement> androidDriver, String locator) {
        return androidDriver.findElement(getXpath(locator));
    }

    public By getXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(AndroidDriver<MobileElement> androidDriver, String locator) {
        waitForElementIsVisible(androidDriver, locator);
        waitForElementClickable(androidDriver, locator);
        getElement(androidDriver, locator).click();
    }

    public MobileElement getDynamicElement(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        locator = getDynamicLocator(locator, params);
        return getElement(androidDriver, locator);
    }

    public String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public By getDynamicXpath(String locator, String... params) {
        return By.xpath(String.format(locator, (Object[]) params));
    }

    public List<MobileElement> getElements(AndroidDriver<MobileElement> androidDriver, String locator) {
        return androidDriver.findElements(getXpath(locator));
    }

    public List<MobileElement> getElements(WebDriver driver, String locator, String... params) {
        return driver.findElements(getDynamicXpath(locator, params));
    }

    public boolean isDisplayElement(AndroidDriver<MobileElement> androidDriver, String locator) {
        waitForElementIsVisible(androidDriver, locator);
        return getElement(androidDriver, locator).isDisplayed();
    }


    public boolean isDisplayElement(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        waitForElementIsVisible(androidDriver, locator, params);
        return getDynamicElement(androidDriver, locator, params).isDisplayed();
    }

    public int getListElementSize(AndroidDriver<MobileElement> androidDriver, String locator) {
        return getElements(androidDriver, locator).size();
    }

    public int getListElementSize(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        return getElements(androidDriver, locator, params).size();
    }

    public void SleepInSecond(double second) {
        try {
            Thread.sleep((long) (second * 1000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkToCheckboxOrRadio(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        MobileElement element = getDynamicElement(androidDriver, locator, params);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(AndroidDriver<MobileElement> androidDriver, String locator) {
        MobileElement element = getElement(androidDriver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        MobileElement element = getDynamicElement(androidDriver, locator, params);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void setImplicitTime(AndroidDriver<MobileElement> androidDriver, long timeInSecond) {
        androidDriver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
    }

    public void doubleClickOnElement(AndroidDriver<MobileElement> androidDriver, String locator) {
        actions = new Actions(androidDriver);
        waitForElementClickable(androidDriver, locator);
        actions.doubleClick(getElement(androidDriver, locator)).perform();
    }

    public void doubleClickOnElement(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        actions = new Actions(androidDriver);
        waitForElementClickable(androidDriver, locator, params);
        actions.doubleClick(getDynamicElement(androidDriver, locator, params)).perform();
    }

    //Tap by coordinates
    public void tapByCoordinates(AndroidDriver<MobileElement> androidDriver, int x, int y) {
        new TouchAction(androidDriver)
                .tap(PointOption.point(x, y))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(GlobalConstants.WAIT_ACTION))).perform();
    }

    //Press by element
    public void pressByElement(AndroidDriver<MobileElement> androidDriver, MobileElement element, long seconds) {
        new TouchAction(androidDriver)
                .press(ElementOption.element(element))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    //Press by coordinates
    public void pressByCoordinates(AndroidDriver<MobileElement> androidDriver, int x, int y, long seconds) {
        new TouchAction(androidDriver)
                .press(PointOption.point(x, y))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    //Horizontal Swipe by percentages
    public void horizontalSwipeByPercentage(AndroidDriver<MobileElement> androidDriver, int startPercentage, int endPercentage, int anchorPercentage) {
        Dimension size = androidDriver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage / 100);
        int startPoint = (int) (size.width * startPercentage / 100);
        int endPoint = (int) (size.width * endPercentage / 100);
        new TouchAction(androidDriver)
                .press(PointOption.point(startPoint, anchor))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(GlobalConstants.WAIT_ACTION)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
    }

    //Vertical Swipe by percentages
    public void verticalSwipeByPercentages(AndroidDriver<MobileElement> androidDriver, int startPercentage, int endPercentage, int anchorPercentage) {
        Dimension size = androidDriver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage / 100);
        int startPoint = (int) (size.height * startPercentage / 100);
        int endPoint = (int) (size.height * endPercentage / 100);
        PointOption start = new PointOption().withCoordinates(anchor, startPoint);
        PointOption end = new PointOption().withCoordinates(anchor, endPoint);
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(start)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(GlobalConstants.WAIT_ACTION)))
                .moveTo(end)
                .release().perform();
    }

    //Swipe by elements
    public void swipeByElements(AndroidDriver<MobileElement> androidDriver, MobileElement startElement, MobileElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        new TouchAction(androidDriver)
                .press(PointOption.point(startX, startY))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(GlobalConstants.WAIT_ACTION)))
                .moveTo(PointOption.point(endX, endY))
                .release().perform();
    }

    //Multitouch action by using an android element
    public void multiTouchByElement(AndroidDriver<MobileElement> androidDriver, MobileElement androidElement) {
        TouchAction press = new TouchAction(androidDriver)
                .press(ElementOption.element(androidElement))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(GlobalConstants.WAIT_ACTION)))
                .release();
        new MultiTouchAction(androidDriver)
                .add(press)
                .perform();

    }

    public void activeApp(AndroidDriver<MobileElement> androidDriver, String bundleId) {
        androidDriver.activateApp(bundleId);
    }
}
