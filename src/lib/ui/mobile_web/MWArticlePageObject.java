package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE_ELEMENT_TPL = "xpath://main[@id='content']//h1[text()='{SUBSTRING}']";
        FOOTER_ELEMENT = "css:footer";
        ADD_ARTICLE_BUTTON = "css:#page-actions #ca-watch:not(.watched)";
        REMOVE_ARTICLE_BUTTON = "css:#page-actions #ca-watch.watched";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
