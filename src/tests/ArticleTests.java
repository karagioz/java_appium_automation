package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String expectedArticleTitle = "Java (programming language)";
        String actualArticleTitle = ArticlePageObject.getArticleTitle(expectedArticleTitle);
        assertEquals("Unexpected article title", expectedArticleTitle, actualArticleTitle);
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchString = "Appium";
        SearchPageObject.typeSearchLine(searchString);
        SearchPageObject.clickByArticleWithSubstring(searchString);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(searchString);
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testAssertArticleHasTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        String articleTitle = "Python (programming language)";
        SearchPageObject.clickByArticleWithSubstring(articleTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertArticleHasTitle(articleTitle);
    }
}
