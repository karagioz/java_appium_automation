package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE_ELEMENT_TPL,
            FOOTER_ELEMENT,
            ADD_ARTICLE_BUTTON,
            REMOVE_ARTICLE_BUTTON,
            GOT_IT_BUTTON,
            SAVED_READING_LIST_TITLE,
            NO_THANKS_BUTTON,
            BACK_BUTTON,
            CANCEL_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
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
        } else if (Platform.getInstance().isIOS()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
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
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }
    }

    public void addArticleToSavedListFirstTime() {
        this.clickOnAddArticleButton();
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
        this.clickOnAddArticleButton();
        this.waitForElementAndClick(
                SAVED_READING_LIST_TITLE,
                "Can't find 'Saved' reading list",
                3);
    }

    public void addArticleToMySavedIOS() {
        this.clickOnAddArticleButton();
    }

    public void clickOnAddArticleButton() {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Can't find button 'Save for later'",
                3);
    }

    public void addArticleToMySavedMW() {
        this.removeArticleFromSavedIfItAdded();
        this.clickOnAddArticleButton();
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(REMOVE_ARTICLE_BUTTON)) {
            this.waitForElementAndClick(
                    REMOVE_ARTICLE_BUTTON,
                    "Can't click button to remove an article from saved",
                    1);
            this.waitForElementPresent(
                    ADD_ARTICLE_BUTTON,
                    "Can't find button to add article to saved list after removing it from there");
        }
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid()) {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        }
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
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
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void backFromArticleToSearchResults() {
        if (Platform.getInstance().isAndroid()) {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        } else if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    BACK_BUTTON,
                    "Can't find Back button",
                    3);
        } else {
            System.out.println("Method backFromArticleToSearchResults() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
