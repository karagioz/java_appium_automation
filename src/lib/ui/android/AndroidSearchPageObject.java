package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static{
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[android.widget.TextView[@text='{TITLE}'] and android.widget.TextView[@text='{DESCRIPTION}']]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SKIP_ELEMENT = "xpath://*[contains(@text, 'Skip')]";
    }


    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
