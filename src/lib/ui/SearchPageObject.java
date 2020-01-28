package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[android.widget.TextView[@text='{TITLE}'] and android.widget.TextView[@text='{DESCRIPTION}']]",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SKIP_ELEMENT = "//*[contains(@text, 'Skip')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
        this.clickOnSkip();
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can't find and click search element", 3);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can't find search input after clicking search init element");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Can't find and type into search input", 3);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath),"Can't find search result with substring " + substring);
    }

    public void waitForSearchResultsToAppear() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Can't find anything by the request",
                10);
    }

    public boolean waitForSearchResultsToDisappear() {
        return this.waitForElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Search results are still present on the page",
                5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath),"Can't find search result with substring " + substring, 3);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can't find close search button");
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Close search button still present on the page", 3);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't find and click close search button", 3);
    }

    public int getAmountOfFoundArticles() {
        this.waitForSearchResultsToAppear();
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Can't find empty result label",
                5);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results by request");
    }

    public void checkSearchInputHint() {
        this.waitForElementAndCheckText(
                By.xpath(SEARCH_INPUT),
                "Can't find search input",
                3);
    }

    public List<WebElement> getListOfArticleTitlesFromSearchResults() {
        this.waitForSearchResultsToAppear();
        return this.getListOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void clickOnSkip() {
        this.waitForElementAndClick(
                By.xpath(SKIP_ELEMENT),
                "Can't find Skip button",
                3);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchResultXpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(By.xpath(searchResultXpath),
                "Can't find search result with title '" + title + "' and description '" + description + "'");
    }
}
