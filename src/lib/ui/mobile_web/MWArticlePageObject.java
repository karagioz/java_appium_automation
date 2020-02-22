package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE_ELEMENT_TPL = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        ADD_ARTICLE_BUTTON = "css:#page-actions #ca-watch";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
