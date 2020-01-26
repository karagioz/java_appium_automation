package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            SAVED_LIST = "//*[@text='Saved']",
            ARTICLE_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getArticleXpathByTitle(String title) {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    public void openSavedFolder() {
        this.waitForElementAndClick(
                By.xpath(SAVED_LIST),
                "Can't find 'Saved' reading list",
                3);
    }

    public void swipeByArticleToDelete(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        waitForArticleToAppearByTitle(title);
        this.swipeElementToLeft(
                By.xpath(articleXpath),
                "Can't find saved article in reading list");
        waitForArticleToDisappearByTitle(title);
    }

    public void waitForArticleToDisappearByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementNotPresent(
                By.xpath(articleXpath),
                "Saved article was not deleted",
                3);
    }

    public void waitForArticleToAppearByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementPresent(
                By.xpath(articleXpath),
                "Saved article not present in the list: " + articleXpath,
                5);
    }

    public void openArticleFromListByTitle(String title) {
        String articleXpath = getArticleXpathByTitle(title);
        this.waitForElementAndClick(
                By.xpath(articleXpath),
                "Article not present in the list",
                3);
    }
}
