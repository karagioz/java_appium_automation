package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE_ELEMENT_TPL,
            FOOTER_ELEMENT,
            ADD_ARTICLE_BUTTON,
            GOT_IT_BUTTON,
            SAVED_READING_LIST_TITLE,
            NO_THANKS_BUTTON,
            BACK_BUTTON,
            CANCEL_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getTitleElementXpath(String substring) {
        return ARTICLE_TITLE_ELEMENT_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement(String title) {
        String titleXpath = getTitleElementXpath(title);
        return this.waitForElementPresent(titleXpath, "Can't find article title " + titleXpath, 15);
    }

    public String getArticleTitle(String title) {
        WebElement titleElement = this.waitForTitleElement(title);
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("content-desc");
        } else {
            return titleElement.getAttribute("name");
        }

    }

    public void assertArticleHasTitle(String title) {
        String titleXpath = getTitleElementXpath(title);
        this.assertElementPresent(titleXpath, "Article title not present");
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }

    }

    public void addArticleToSavedListFirstTime() {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Can't find button 'Add article to list'",
                3);
        this.waitForElementAndClick(
                GOT_IT_BUTTON,
                "Can't find GOT IT",
                3);
        this.waitForElementAndClick(
                SAVED_READING_LIST_TITLE,
                "Can't find 'Saved' reading list",
                3);
    }

    public void addArticleToSavedListNotFirstTime() {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Can't find button 'Add article to list'",
                3);
        this.waitForElementAndClick(
                SAVED_READING_LIST_TITLE,
                "Can't find 'Saved' reading list",
                3);
    }

    public void addArticleToMySavedIOS() {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Can't find button 'Save for later'",
                3);
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid()) {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        }
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Can't find Back button",
                3);
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    NO_THANKS_BUTTON,
                    "Can't find 'No thanks' button",
                    3);
        } else {
            this.waitForElementAndClick(
                    CANCEL_BUTTON,
                    "Can't find 'Cancel' button",
                    3);
        }
    }

    public void backFromArticleToSearchResults() {
        if (Platform.getInstance().isAndroid()) {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        } else {
            this.waitForElementAndClick(
                    BACK_BUTTON,
                    "Can't find Back button",
                    3);
        }
    }
}
