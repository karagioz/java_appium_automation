package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        SAVED_LIST = "xpath://*[@text='Saved']";
        ARTICLE_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
