package com.smartcarmobile.pages;

import com.smartcarmobile.utilities.GlobalParams;
import com.smartcarmobile.utilities.PropertyManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_serialnumber")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow/XCUIElementTypeOther[2]")
    public WebElement serialNumberField;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btn_register_serialnumber_continue")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Continue\"]")
    public WebElement continueBtn;

    @AndroidFindBy(id = "android:id/alertTitle")
//    @iOSXCUITFindBy(xpath = "")
    public WebElement errorText;

    public void enterRegistrationSerialNumber() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                String serialNumberAndroid = PropertyManager.getProperty("register.device.key.android");
                serialNumberField.sendKeys(serialNumberAndroid);
                break;

            case "ios":
                String serialNumberiOS = PropertyManager.getProperty("register.device.key.ios");
                serialNumberField.sendKeys(serialNumberiOS);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void clickContinueBtn() {
        waitAndClickElement(continueBtn);
    }
}
