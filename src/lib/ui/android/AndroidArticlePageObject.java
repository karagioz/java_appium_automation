package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE_ELEMENT_TPL = "xpath://android.view.View[@content-desc='{SUBSTRING}']";
        FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link";
        ADD_ARTICLE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        SAVED_READING_LIST_TITLE = "id:org.wikipedia:id/item_title";
        NO_THANKS_BUTTON = "xpath://*[@resource-id='android:id/button2']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
