package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        androidDriver.manage().timeouts().implicitlyWait(GlobalConstants.TIME_OUT, TimeUnit.SECONDS);
    }

    public void rightClickOnElement(AndroidDriver<MobileElement> androidDriver, String locator, String... params) {
        actions = new Actions(androidDriver);
        waitForElementClickable(androidDriver, locator, params);
        actions.contextClick(getDynamicElement(androidDriver, locator, params)).perform();
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

    public void dragAndDropElement(AndroidDriver<MobileElement> androidDriver, String sourceLocator, String targetLocator) {
        waitForElementIsVisible(androidDriver, sourceLocator);
        waitForElementIsVisible(androidDriver, targetLocator);
        actions = new Actions(androidDriver);
        actions.dragAndDrop(getElement(androidDriver, sourceLocator), getElement(androidDriver, targetLocator)).perform();
    }

    public void pressKeyToElement(AndroidDriver<MobileElement> androidDriver, String locator, Keys keys) {
        waitForElementIsVisible(androidDriver, locator);
        actions = new Actions(androidDriver);
        actions.sendKeys(getElement(androidDriver, locator), keys).perform();
    }

    public void pressKeyToElement(AndroidDriver<MobileElement> androidDriver, String locator, Keys keys, String... params) {
        waitForElementIsVisible(androidDriver, locator, params);
        actions = new Actions(androidDriver);
        actions.sendKeys(getDynamicElement(androidDriver, locator, params), keys).perform();
    }
}
