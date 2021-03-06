package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String
            login = "DariaTest",
            password = "t3stp4ss";

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
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySavedIOS();
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.clickOnAddArticleButton();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement(articleTitle);
            assertEquals("We are not on the same page after login",
                    articleTitle,
                    ArticlePageObject.getArticleTitle(articleTitle));
            ArticlePageObject.addArticleToMySavedMW();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
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
        String firstArticleSubTitle = "General-purpose, high-level programming language";
        String secondArticleTitle = "Python syntax and semantics";
        String secondArticleSubTitle = "Syntax of the Python programming language";
        SearchPageObject.clickByArticleWithSubstring(firstArticleSubTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(firstArticleTitle);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToSavedListFirstTime();
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySavedIOS();
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.clickOnAddArticleButton();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement(firstArticleTitle);
            assertEquals("We are not on the same page after login",
                    firstArticleTitle,
                    ArticlePageObject.getArticleTitle(firstArticleTitle));
            ArticlePageObject.addArticleToMySavedMW();
        }
        ArticlePageObject.backFromArticleToSearchResults();
        if (Platform.getInstance().isMW()){
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchLine);
        }
        SearchPageObject.clickByArticleWithSubstring(secondArticleSubTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToSavedListNotFirstTime();
            this.appPressBackButton();
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySavedIOS();
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticleToMySavedMW();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openSavedFolder();
        MyListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        MyListsPageObject.openArticleFromListByTitle(secondArticleTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
    }
}
