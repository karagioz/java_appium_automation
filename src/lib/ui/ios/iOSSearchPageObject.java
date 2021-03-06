package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static{
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Clear text";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@label='Search Wikipedia'][following-sibling::XCUIElementTypeButton[@name='Cancel']]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[@name='{TITLE}\n{DESCRIPTION}']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        SKIP_ELEMENT = "xpath://XCUIElementTypeButton[@name='Skip']";
    }

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
