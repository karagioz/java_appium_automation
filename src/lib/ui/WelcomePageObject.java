package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Learn more about Wikipedia\"]",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Add or edit preferred languages\"]",
        STEP_LEAR_MORE_ABOUT_DATA_COLLECTED_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Learn more about data collected\"]",
        NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Next\"]",
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Get started\"]";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Can't find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Can't find 'New ways to explore' text", 10);
    }

    public void waitForAddOrEditPreferredLanguagesText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT,
                "Can't find 'Add or edit preferred languages' text", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEAR_MORE_ABOUT_DATA_COLLECTED_TEXT,
                "Can't find 'Learn more about data collected' text", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON,
                "Can't find 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Can't find 'Get started' button", 10);
    }
}
