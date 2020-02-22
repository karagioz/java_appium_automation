package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
