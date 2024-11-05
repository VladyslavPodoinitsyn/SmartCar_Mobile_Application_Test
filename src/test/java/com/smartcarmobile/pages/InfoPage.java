package com.smartcarmobile.pages;

import com.smartcarmobile.utilities.GlobalParams;
import com.smartcarmobile.utilities.TestUtils;
import com.github.javafaker.Faker;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class InfoPage extends BasePage{

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
    public WebElement emailInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_firstname")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]")
    public WebElement firstNameInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_lastname")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[3]")
    public WebElement lastNameInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/spinner")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
    public WebElement phoneDropdown;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.view.ViewGroup[1]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePickerWheel[`value == \"+1\"`]")
    public WebElement selectPhoneCode;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/editText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField")
    public WebElement phoneInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_country")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[4]")
    public WebElement countryDropdown;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePickerWheel[`value == \"United States\"`]")
    public WebElement selectCountry;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_streetaddress")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[5]")
    public WebElement streetAddressInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_city")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[6]")
    public WebElement cityInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_state")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[7]")
    public WebElement stateDropdown;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[contains(@text, 'Washington')]") ///hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[8]
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePickerWheel[`value == \"Washington\"`]")
    public WebElement selectState;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/register_zipcode")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"SmartCarMobile\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTextField[8]")
    public WebElement zipCodeInputRegister;

    @AndroidFindBy(id = "com.whiz.smartcarapp:id/btn_register_user_information_complete_registration")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Submit\"]")
    public WebElement submitBtn;

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Registration, Successfully Registered\"]")
    public WebElement registerText;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Info']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Info\"]")
    public WebElement infoPageTitle;


    public void registerFakeData() {
        Faker faker = new Faker();
        waitUntilElementPresentAndVisible(emailInputRegister);
        emailInputRegister.sendKeys(faker.internet().emailAddress());
        clickInfoLabel();
        waitUntilElementPresentAndVisible(firstNameInputRegister);
        firstNameInputRegister.sendKeys(faker.name().firstName());
        waitUntilElementPresentAndVisible(lastNameInputRegister);
        lastNameInputRegister.sendKeys(faker.name().lastName());
        clickPhoneDropdown();
        TestUtils.waitFor(1000);
        selectPhoneCode();
        TestUtils.waitFor(1000);
        //phoneInputRegister.sendKeys(faker.number().digits(10));
        enterRandomNumber();
        clickCountryDropdown();
        TestUtils.waitFor(1000);
        selectCountry();
        waitUntilElementPresentAndVisible(streetAddressInputRegister);
        streetAddressInputRegister.sendKeys(faker.address().streetAddress());
        waitUntilElementPresentAndVisible(cityInputRegister);
        cityInputRegister.sendKeys(faker.address().cityName());
        clickStateDropdown();
        TestUtils.waitFor(1000);
        selectState();
        waitUntilElementPresentAndVisible(zipCodeInputRegister);
        zipCodeInputRegister.sendKeys(faker.number().digits(5));
        clickInfoLabel();
    }

    public void scrollToStateAndClick() {
        scrollToElementAndClick(selectState);
    }

    public void enterRandomNumber() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10);
            String numberString = String.valueOf(randomNumber);
            phoneInputRegister.sendKeys(numberString);
        }
    }

    public String getTxt() {
        waitUntilElementPresentAndVisible(registerText);
        String text = getText(registerText);
        return text;
    }

    public void selectPhoneCode() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                selectPhoneCode.click();
                break;
            case "ios":
                selectPickerWheelValue("+1");
                    break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
                }
        }

    public void selectCountry() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                selectCountry.click();
                break;
            case "ios":
                selectPickerWheelValue("United States");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void selectState() {
        switch (new GlobalParams().getPlatformName()) {
            case "android":
                scrollToStateAndClick();
                break;
            case "ios":
                selectPickerWheelValue("Washington");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void clickSubmit() {
        waitAndClickElement(submitBtn);
    }

    public void clickInfoLabel() {
        waitAndClickElement(infoPageTitle);
    }

    public void clickPhoneDropdown() {
        waitAndClickElement(phoneDropdown);
    }

    public void clickCountryDropdown() {
        waitAndClickElement(countryDropdown);
    }

    public void clickStateDropdown() {
        waitAndClickElement(stateDropdown);
    }
}
