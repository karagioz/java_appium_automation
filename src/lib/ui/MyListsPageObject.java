package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            SAVED_LIST,
            ARTICLE_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON_TPL;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getArticleXpathByTitle(String title) {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", title);
    }

    private static String getRemoveButtonByTitle(String title) {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    public void openSavedFolder() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    SAVED_LIST,
                    "Can't find 'Saved' reading list",
                    3);
        }
    }

    public void swipeByArticleToDelete(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        waitForArticleToAppearByTitle(title);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    articleXpath,
                    "Can't find saved article in reading list");
            if (Platform.getInstance().isIOS()) {
                this.clickElementToTheRightUpperCorner(articleXpath,
                        "Can't find saved article in reading list");
            }
        } else {
            String removeLocator = getRemoveButtonByTitle(title);
            this.waitForElementAndClick(
                    removeLocator,
                    "Can't click button to remove article from saved",
                    10);
            driver.navigate().refresh();
        }
        waitForArticleToDisappearByTitle(title);
    }

    public void waitForArticleToDisappearByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementNotPresent(
                articleXpath,
                "Saved article was not deleted",
                3);
    }

    public void waitForArticleToAppearByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementPresent(
                articleXpath,
                "Saved article not present in the list: " + articleXpath,
                15);
    }

    public void openArticleFromListByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementAndClick(
                articleXpath,
                "Article not present in the list",
                3);
    }
}
