package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = "Java (programming language)";
        ArticlePageObject.waitForTitleElement(articleTitle);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToSavedListFirstTime();
        } else {
            ArticlePageObject.addArticleToMySavedIOS();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openSavedFolder();
        MyListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOneOfThem() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        String firstArticleTitle = "Python (programming language)";
        String secondArticleTitle = "Python syntax and semantics";
        SearchPageObject.clickByArticleWithSubstring(firstArticleTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(firstArticleTitle);
        ArticlePageObject.addArticleToSavedListFirstTime();
        this.appPressBackButton();
        SearchPageObject.clickByArticleWithSubstring(secondArticleTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
        ArticlePageObject.addArticleToSavedListNotFirstTime();
        this.appPressBackButton();
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openSavedFolder();
        MyListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        MyListsPageObject.openArticleFromListByTitle(secondArticleTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
    }
}
