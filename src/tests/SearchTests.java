package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results!",
                amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "fdklndfkfklrejkrelj";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckHint() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.checkSearchInputHint();
    }

    @Test
    public void testSearchCheckArticlesFoundAndCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        List articles = SearchPageObject.getListOfArticleTitlesFromSearchResults();
        assertTrue("Search results do not contain any articles", articles.size() > 0);
        SearchPageObject.clickCancelSearch();
        boolean areSearchResultsCleared = SearchPageObject.waitForSearchResultsToDisappear();
        assertTrue("Search results still present on the page", areSearchResultsCleared);
    }

    @Test
    public void testCheckKeyWordInSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        List<WebElement> articles = SearchPageObject.getListOfArticleTitlesFromSearchResults();
        assertTrue("Search results do not contain any articles", articles.size() > 0);
        for (WebElement article: articles) {
            String articleTitle = article.getAttribute("text");
            assertTrue(
                    "Article title doesn't contain the search keyword",
                    articleTitle.contains(searchLine));
        }
    }

    @Test
    public void testSearchByTitleAndDescription() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Java";
        Map<String, String> articles = new HashMap<String, String>();
        articles.put("Java", "Island of Indonesia");
        articles.put("Java (programming language)", "Object-oriented programming language");
        articles.put("JavaScript", "Programming language");
        SearchPageObject.typeSearchLine(searchLine);
        for ( String title : articles.keySet() ) {
            String description = articles.get(title);
            SearchPageObject.waitForElementByTitleAndDescription(title, description);
        }
    }
}
