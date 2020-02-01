package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_BUTTON,
                "Can't find 'My lists' button",
                3);
    }
}
