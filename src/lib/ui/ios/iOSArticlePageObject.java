package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_ELEMENT_TPL = "id:{SUBSTRING}";
        FOOTER_ELEMENT = "id:Official website";
        ADD_ARTICLE_BUTTON = "id:Save for later";
        BACK_BUTTON = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
