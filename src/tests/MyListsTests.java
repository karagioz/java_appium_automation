package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleSubTitle = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(articleSubTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String articleTitle = "Java (programming language)";
        ArticlePageObject.waitForTitleElement(articleTitle);
        ArticlePageObject.addArticleToSavedListFirstTime();
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openSavedFolder();
        MyListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOneOfThem() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Python";
        SearchPageObject.typeSearchLine(searchLine);
        String firstArticleTitle = "Python (programming language)";
        String secondArticleTitle = "Python syntax and semantics";
        SearchPageObject.clickByArticleWithSubstring(firstArticleTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement(firstArticleTitle);
        ArticlePageObject.addArticleToSavedListFirstTime();
        this.appPressBackButton();
        SearchPageObject.clickByArticleWithSubstring(secondArticleTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
        ArticlePageObject.addArticleToSavedListNotFirstTime();
        this.appPressBackButton();
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openSavedFolder();
        MyListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        MyListsPageObject.openArticleFromListByTitle(secondArticleTitle);
        ArticlePageObject.waitForTitleElement(secondArticleTitle);
    }
}
