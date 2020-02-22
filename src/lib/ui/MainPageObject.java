package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 3);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeOutInSeconds) {
        MobileElement element = (MobileElement) waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.setValue(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));

    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void waitForElementAndCheckText(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        String elementText = element.getAttribute("text");
        Assert.assertEquals("Unexpected text in element", "Search Wikipedia", elementText);
    }


    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            alreadySwiped ++;
        }
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            alreadySwiped ++;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Can't find element by locator", 3).
                getLocation().
                getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, errorMessage);
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(rightX, middleY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                    .moveTo(PointOption.point(leftX, middleY))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator + "/..", errorMessage);
            int rightX = element.getLocation().getX();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            int width = element.getSize().getWidth();
            int pointToClickX = (rightX + width) - 3;
            int pointToClickY = middleY;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List<WebElement> getListOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        return driver.findElements(by);
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage,
                                                long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            String defaultMessage = "An element '" + locator + "' supposed to be present.\n";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else if (byType.equals("css")) {
            return By.cssSelector(locator);
        } else if (byType.equals("class")) {
            return By.className(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }
}
