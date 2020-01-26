package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String expectedArticleTitle = "Java (programming language)";
        String titleBeforeRotation = ArticlePageObject.getArticleTitle(expectedArticleTitle);
        this.rotateScreenLandscape();
        String titleAfterRotation = ArticlePageObject.getArticleTitle(expectedArticleTitle);
        assertEquals("Article title have been changed after rotation",
                titleBeforeRotation,
                titleAfterRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle(expectedArticleTitle);
        assertEquals("Article title have been changed after second rotation",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
