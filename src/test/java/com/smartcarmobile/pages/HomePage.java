package com.smartcarmobile.pages;

import com.smartcarmobile.utilities.GlobalParams;
import com.smartcarmobile.utilities.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tv_home_premium_plan_vehicle_name")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Test car\"]")
    public WebElement vehicleName;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.FrameLayout/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"icon_home_gray\"]")
    public WebElement homeTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Track\"]/android.widget.FrameLayout/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"icon_track_gray\"]")
    public WebElement trackTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Controls\"]/android.widget.FrameLayout/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"icon_controls_gray\"]")
    public WebElement controlsTab;

    @AndroidFindBy(id = "//android.widget.FrameLayout[@content-desc=\"Activity\"]/android.widget.FrameLayout/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"icon_activity_gray\"]")
    public WebElement activityTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Setup\"]/android.widget.FrameLayout/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"icon_settings_gray\"]")
    public WebElement setupTab;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tv_home_location_address")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther")
    public WebElement locationSection;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/iv_home_premium_plan_vehicle_profile")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
    public WebElement vehicleImage;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    public WebElement acceptNotificationsBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tv_desc")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label CONTAINS 'Test car has used' AND label CONTAINS 'of its data plan for this period.'`]")
    public WebElement dataUsageAlert;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tvLTE")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"LTE\"]")
    public WebElement dashCamLteBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tvWiFi")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Wi-Fi\"]")
    public WebElement dashCamWiFiBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tvTapForLiveViewLabel")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label CONTAINS 'Tap for Live View'`]") ////XCUIElementTypeStaticText[@name="Tap for Live View over Wi-Fi"]
    public WebElement dashCamLiveViewLabel;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tvSubtitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Test car has used 100% of its data plan for this period. If you need access to Live View now, please upgrade your Streaming Plan.\"]")
    public WebElement liveViewNotEnoughData;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/iv_toolbar_title")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"logo\"]")
    public WebElement homeScreenLogo;

    public void clickLocationSection() {
        waitAndClickElement(locationSection);
    }

    public void clickSetupTab() {
        waitAndClickElement(setupTab);
    }

    public void clickVehicleImage() {
        waitAndClickElement(vehicleImage);
    }

    public void homeScreenLogoPresentAndVisible() {
        waitUntilElementPresentAndVisible(homeScreenLogo);
    }

    public String getVehicleName() {
        waitUntilElementPresentAndVisible(vehicleName);
        return vehicleName.getText();
    }

    public String getDataUsageText() {
        waitUntilElementPresentAndVisible(dataUsageAlert);
        return dataUsageAlert.getText();
    }

    public void acceptNotifications() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                waitAndClickElement(acceptNotificationsBtn);
                break;
            case "ios":
                WebDriverWait iOSwait = new WebDriverWait(driver, Duration.ofSeconds(15));
                iOSwait.until(ExpectedConditions.alertIsPresent());
                final String iosLocator = "**/XCUIElementTypeButton[`name == \"Allow\"`]";
                driver.setSetting("acceptAlertButtonSelector", iosLocator);
                try {
                    driver.switchTo().alert().accept();
                } catch (Exception ignored) {
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void scrollToDashCamWidget() {
        scrollToElement(dashCamLteBtn);
    }

    public void startLiveStream(String connectionType) {
        // Handle LTE
        if (connectionType.equals("LTE")) {
            if (!dashCamLiveViewLabel.getText().equals("Tap for Live View")) {
                waitAndClickElement(dashCamLteBtn);
            }
            waitAndClickElement(dashCamLiveViewLabel);
        }
        // Handle Wi-Fi
        if (connectionType.equals("Wi-Fi")) {
            if (!dashCamLiveViewLabel.getText().equals("Tap for Live View over Wi-Fi")) {
                waitAndClickElement(dashCamWiFiBtn);
            }
            waitAndClickElement(dashCamLiveViewLabel);
        }
    }

    public void refreshPage() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                scrollOrSwipe("down", 500);
                TestUtils.waitFor(8000);
                break;
            case "ios":
                scrollOrSwipe("down", 300);
                TestUtils.waitFor(4000);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }
}
