package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_BUTTON,
                "Can't find 'My lists' button",
                3);
    }
}
