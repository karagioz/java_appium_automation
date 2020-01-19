import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {

    private static AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/Karagioz/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Can't find search input",
                3);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by 'Java'",
                10);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Something",
                "Can't find search input",
                3);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find close search button",
                3);
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Close search button still present on the page",
                3
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Can't find search input",
                3);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by 'Java'",
                10);
        WebElement titleElement = waitForElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "Can't find article title",
                15);
        String articleTitle = titleElement.getAttribute("content-desc");
        Assert.assertEquals("Unexpected article title", "Java (programming language)", articleTitle);
    }
    
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "Can't find search input",
                3);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Appium']"),
                "Can't find 'Appium'",
                10);
        waitForElementPresent(
                By.xpath("(//android.view.View[@content-desc=\"Appium\"])[1]"),
                "Can't find article title",
                15);
        swipeUpToFindElement(
                By.id("org.wikipedia:id/page_external_link"),
                "Cannot find the end of the article",
                20);
    }

    @Test
    public void testCheckHint() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndCheckText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Can't find search input",
                3);
    }

    @Test
    public void testSearchCheckAndCancel() {
        List<WebElement> articles = searchForValueAndReturnResults("Python");
        Assert.assertTrue("Search results do not contain any articles", articles.size() > 0);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find close search button",
                3);
        boolean areSearchResultsCleared = waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']"),
                "Search results are still present on the page",
                3
        );
        Assert.assertTrue("Search results still present on the page", areSearchResultsCleared);
    }

    @Test
    public void testCheckKeyWordInSearchResults() {
        String keyWord = "Python";
        List<WebElement> articles = searchForValueAndReturnResults(keyWord);
        Assert.assertTrue("Search results do not contain any articles", articles.size() > 0);
        for (WebElement article: articles) {
            String articleTitle = article.getAttribute("text");
            Assert.assertTrue(
                    "Article title doesn't contain the search keyword",
                    articleTitle.contains(keyWord));
        }
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Can't find search input",
                3);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language'",
                10);
        waitForElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "Can't find article title",
                15);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Can't find button 'Add article to list'",
                3);
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find GOT IT",
                3);
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/create_button"),
//                "Can't find 'Create new' button",
//                3);
//        waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                "Learning programming",
//                "Can't find input for folder name",
//                3);
//        waitForElementAndClick(
//                By.xpath("//*[@text='OK']"),
//                "Can't find OK button",
//                3);
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Can't find 'Saved' reading list",
                3);
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Can't find Back button",
                3
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Can't find 'No thanks' button",
                3);
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView"),
                "Can't find 'My lists' button",
                3);
        waitForElementAndClick(
                By.xpath("//*[@text='Saved']"),
                "Can't find 'Saved' reading list",
                3);
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find saved article in reading list"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Saved article was not deleted",
                3
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        String searchLine = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchLine  ,
                "Can't find search input",
                3);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Can't find anything by the request " + searchLine,
                5);
        int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue(
                "We found too few results!",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        String searchLine = "fdklndfkfklrejkrelj";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchLine  ,
                "Can't find search input",
                3);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String emptyResultLocator = "//*[@text='No results found']";
        waitForElementPresent(
                By.xpath(emptyResultLocator),
                "Can't find empty result label",
                5
        );
        assertElementNotPresent(
                By.xpath(searchResultLocator),
                "We've found some results by request: " + searchLine
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        String searchLine = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchLine,
                "Can't find search input",
                3);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language'",
                10);
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "content-desc",
                "Can't find title of article",
                5);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "content-desc",
                "Can't find title of article",
                5);
        Assert.assertEquals("Article title have been changed after rotation",
                titleBeforeRotation,
                titleAfterRotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "content-desc",
                "Can't find title of article",
                5);
        Assert.assertEquals("Article title have been changed after second rotation",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 3);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));

    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    private void waitForElementAndCheckText(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        String elementText = element.getAttribute("text");
        Assert.assertEquals("Unexpected text in element", "Search Wikipedia", elementText);
    }

    private List<WebElement> searchForValueAndReturnResults(String value) {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Can't find Skip button",
                3);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find search element",
                3);
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Python",
                "Can't find search input",
                3);
        WebElement searchResults = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']"),
                "Can't find search results",
                10);
        List<WebElement> articles = searchResults.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        return articles;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
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
    }

    protected void swipeUpQuick() {
        swipeUp(20);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            alreadySwiped ++;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(leftX, middleY))
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }
}
