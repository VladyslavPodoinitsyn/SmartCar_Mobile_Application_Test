package com.smartcarmobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SetupPage extends BasePage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow/XCUIElementTypeOther")
    public WebElement accountSection;

    public void clickAccountSection() {
        waitAndClickElement(accountSection);
    }
}
