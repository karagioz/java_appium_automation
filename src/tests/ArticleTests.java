package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String expectedArticleTitle = "Java (programming language)";
        String actualArticleTitle = ArticlePageObject.getArticleTitle(expectedArticleTitle);
        assertEquals("Unexpected article title", expectedArticleTitle, actualArticleTitle);
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchString = "Appium";
        SearchPageObject.typeSearchLine(searchString);
        SearchPageObject.clickByArticleWithSubstring(searchString);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement(searchString);
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testAssertArticleHasTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        String articleTitle = "Python (programming language)";
        SearchPageObject.clickByArticleWithSubstring(articleTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleHasTitle(articleTitle);
    }
}
