package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_TITLE_ELEMENT_TPL = "//android.view.View[@content-desc=\"{SUBSTRING}\"]",
            FOOTER_ELEMENT = "org.wikipedia:id/page_external_link",
            ADD_ARTICLE_BUTTON = "org.wikipedia:id/article_menu_bookmark",
            GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
            SAVED_READING_LIST_TITLE = "org.wikipedia:id/item_title",
            NO_THANKS_BUTTON = "//*[@resource-id='android:id/button2']";

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
        return this.waitForElementPresent(By.xpath(titleXpath), "Can't find article title", 15);
    }

    public String getArticleTitle(String title) {
        WebElement titleElement = this.waitForTitleElement(title);
        return titleElement.getAttribute("content-desc");
    }

    public void assertArticleHasTitle(String title) {
        String titleXpath = getTitleElementXpath(title);
        this.assertElementPresent(By.xpath(titleXpath), "Article title not present");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.id(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20);
    }

    public void addArticleToSavedListFirstTime() {
        this.waitForElementAndClick(
                By.id(ADD_ARTICLE_BUTTON),
                "Can't find button 'Add article to list'",
                3);
        this.waitForElementAndClick(
                By.id(GOT_IT_BUTTON),
                "Can't find GOT IT",
                3);
        this.waitForElementAndClick(
                By.id(SAVED_READING_LIST_TITLE),
                "Can't find 'Saved' reading list",
                3);
    }

    public void addArticleToSavedListNotFirstTime() {
        this.waitForElementAndClick(
                By.id(ADD_ARTICLE_BUTTON),
                "Can't find button 'Add article to list'",
                3);
        this.waitForElementAndClick(
                By.id(SAVED_READING_LIST_TITLE),
                "Can't find 'Saved' reading list",
                3);
    }

    public void closeArticle() {
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
        this.waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Can't find Back button",
                3);
        this.waitForElementAndClick(
                By.xpath(NO_THANKS_BUTTON),
                "Can't find 'No thanks' button",
                3);
    }
}
