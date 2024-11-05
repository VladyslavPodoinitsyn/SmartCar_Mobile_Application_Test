package com.smartcarmobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage {

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/et_user_details_firstname")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]")
    public WebElement firstName;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btn_user_details_logout")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Log Out\"]")
    public WebElement logOutBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tvAccountDeletionTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Account Deletion\"]")
    public WebElement accountDeletionSection;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btnDeleteAccount")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Delete Account\"]")
    public WebElement deleteAccountBtn;

    @AndroidFindBy(id = "android:id/button1")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Confirm\"]")
    public WebElement deleteAccountConfirmBtn;

    public String getAccountFirstName() {
        waitUntilElementPresentAndVisible(firstName);
        return firstName.getText();
    }

    public void scrollToLogOutBtnAndClick() {
        scrollToElementAndClick(logOutBtn);
    }

    public void scrollToAccountDeletionSectionAndClick() {
        scrollToElementAndClick(accountDeletionSection);
    }

    public void clickDeleteAccountBtn() {
        waitAndClickElement(deleteAccountBtn);
    }

    public void clickDeleteAccountConfirmBtn() {
        waitAndClickElement(deleteAccountConfirmBtn);
    }

}
