package com.smartcarmobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RegisterYourSystemPage extends BasePage {

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tv_register_your_system")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Register Your System\"]")
    public WebElement registerYouSystemBtn;

    public void clickRegisterYouSystemBtn() {
        waitAndClickElement(registerYouSystemBtn);
    }
}


