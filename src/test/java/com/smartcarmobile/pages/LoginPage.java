package com.smartcarmobile.pages;

import com.smartcarmobile.utilities.*;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.whiz.smartcarapp:id/textInputEditText' and @text='Email']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField")
    public WebElement emailInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.whiz.smartcarapp:id/textInputEditText' and @text='Password']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField")
    public WebElement passwordInput;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btn_login_login")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Log In\"]")
    public WebElement logInBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btn_login_register")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Register\"]")
    public WebElement registerBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/alertTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Login failed\"]")
    public WebElement errorText;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/textinput_error")
    public WebElement emailFieldError;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/textinput_error")
    public WebElement passwordFieldError;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btnAccountDeletedOkay")
//    @iOSXCUITFindBy(xpath = "")
    public WebElement accountDeletionOkBtn;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/tv_login_make_your_car_smarter")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Your Car, Connected.\"]")
    public WebElement loginScreenText;

    public void login() {
        String email = PropertyManager.getProperty("user.email");
        String password = PropertyManager.getProperty("user.password");
        waitUntilElementPresentAndVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        waitUntilElementPresentAndVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        clickLogInBtn();
    }

    public void sharedUserAccountLogin() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                loginForDeletion(PropertyManager.getProperty("shared.user.email.android"), PropertyManager.getProperty("shared.user.password"));
                break;
            case "ios":
                loginForDeletion(PropertyManager.getProperty("shared.user.email.ios"), PropertyManager.getProperty("shared.user.password"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void loginForDeletion(String email, String password) {
        waitUntilElementPresentAndVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        waitUntilElementPresentAndVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        clickLogInBtn();
    }

    public String getErrTxt() {
        waitUntilElementPresentAndVisible(errorText);
        return errorText.getText();
    }

    public String getLoginScreenTxt() {
        waitUntilElementPresentAndVisible(loginScreenText);
        return loginScreenText.getText();
    }

    public String findErrorText(String field) {
        String errorText;

        switch (field) {
            case "email":
                waitUntilElementPresentAndVisible(emailFieldError);
                errorText = getText(emailFieldError);
                break;
            case "password":
                waitUntilElementPresentAndVisible(passwordFieldError);
                errorText = getText(passwordFieldError);
                break;
            default:
                // Handle the case when an invalid field name is provided
                errorText = "Invalid field name";
                break;
        }
        return errorText;
    }

    public void clickAccountDeletionOkBtn() {
        scrollToElementAndClick(accountDeletionOkBtn);
    }

    public void clickRegisterBtn() {
        waitAndClickElement(registerBtn);
    }

    public void clickLogInBtn() {
        waitAndClickElement(logInBtn);
    }

    public String waitUntilElementPresentAndVisible(String field) {

        switch (field) {
            case "email":
                waitUntilElementPresentAndVisible(emailInput);
                break;
            case "password":
                waitUntilElementPresentAndVisible(passwordInput);
                break;
        }
        return field;
    }
}
