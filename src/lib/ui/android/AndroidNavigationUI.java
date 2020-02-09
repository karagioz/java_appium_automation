package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
