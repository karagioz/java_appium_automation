package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {
    private static final String PLATFROM_IOS = "ios";
    private static final String PLATFROM_ANDROID = "android";
    private static final String PLATFROM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;
    private Platform() {}
    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFROM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFROM_IOS);
    }

    public boolean isMW() { return isPlatform(PLATFROM_MOBILE_WEB); }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/Karagioz/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 11 Pro Max");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("app",
                "/Users/Karagioz/IdeaProjects/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("connectHardwareKeyboard", "false");
        capabilities.setCapability("sendKeyStrategy", "grouped");
        capabilities.setCapability("noReset", "false");
        return capabilities;
    }

    private boolean isPlatform(String myPlatform) {
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
