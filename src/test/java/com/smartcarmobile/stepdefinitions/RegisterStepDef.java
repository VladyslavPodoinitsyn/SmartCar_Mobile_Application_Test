package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.*;
import com.smartcarmobile.utilities.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterStepDef {

    TestUtils utils = new TestUtils();
    LoginPage loginPage = new LoginPage();
    RegisterYourSystemPage registerYourSystemPage = new RegisterYourSystemPage();
    RegistrationPage registrationPage = new RegistrationPage();
    InfoPage infoPage = new InfoPage();

    @Given("user is on the Login screen")
    public void user_is_on_the_login_screen() {
        loginPage.getLoginScreenTxt();
    }

    @When("user clicks the Register button")
    public void userClicksTheRegisterButton() {
        loginPage.clickRegisterBtn();
    }

    @And("user clicks the Register You System button")
    public void userClicksTheRegisterYouSystemButton() {
        registerYourSystemPage.clickRegisterYouSystemBtn();
    }

    @And("user enters the serial number")
    public void userEntersTheSerialNumber() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        utils.updateDeviceState();
        registrationPage.enterRegistrationSerialNumber();
    }

    @And("user clicks the Continue button")
    public void userClicksTheContinueButton() {
        registrationPage.clickContinueBtn();
    }

    @And("user enters his data")
    public void userEntersHisData() {
        infoPage.registerFakeData();
    }

    @And("user clicks the Submit button")
    public void userClicksTheButton() {
        infoPage.clickSubmit();
    }

    @Then("Pop-up window {string} should be displayed")
    public void pop_up_window_should_be_displayed(String expectedRegisterText) {
        System.out.println("Expected register text is " + expectedRegisterText);
        String actualRegisterText = infoPage.getTxt();
        System.out.println("Actual register text is - " + actualRegisterText);
        Assert.assertEquals("Actual register text is not as expected register text!", expectedRegisterText, actualRegisterText);
    }

//    @Then("Pop-up window should be displayed")
//    public void pop_up_window_should_be_displayed() {
//        switch (new GlobalParams().getPlatformName()) {
//            case "android":
//                String expectedRegisterTextAndroid = "Registration Complete";
//                System.out.println("Expected register text is " + expectedRegisterTextAndroid);
//                String actualRegisterTextAndroid = infoPage.getTxt();
//                System.out.println("Actual register text is - " + actualRegisterTextAndroid);
//                Assert.assertEquals("Actual register text is not as expected register text!", expectedRegisterTextAndroid, actualRegisterTextAndroid);
//                break;
//            case "ios":
//                String expectedRegisterTextiOS = "Registration, Successfully Registered";
//                System.out.println("Expected register text is " + expectedRegisterTextiOS);
//                String actualRegisterTextiOS = infoPage.getTxt();
//                System.out.println("Actual register text is - " + actualRegisterTextiOS);
//                Assert.assertEquals("Actual register text is not as expected register text!", expectedRegisterTextiOS, actualRegisterTextiOS);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
//        }
//    }

}


