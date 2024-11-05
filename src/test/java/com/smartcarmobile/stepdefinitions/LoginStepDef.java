package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    SetupPage setupPage = new SetupPage();
    AccountPage accountPage = new AccountPage();

    @When("user enters username {string}")
    public void user_enters_username(String email) {
        loginPage.waitUntilElementPresentAndVisible("email");
        loginPage.emailInput.clear();
        loginPage.emailInput.sendKeys(email);
    }
    @And("user enters the password {string}")
    public void user_enters_the_password(String password) {
        loginPage.waitUntilElementPresentAndVisible("password");
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
    }

    @And("user clicks the Log In button")
    public void user_clicks_the_button() {
        loginPage.clickLogInBtn();
        homePage.acceptNotifications();
    }

    @And("user is on the Home screen")
    public void user_is_on_the_home_screen() {
        homePage.homeScreenLogoPresentAndVisible();
    }

    @Then("account holder name should be {string}")
    public void account_holder_name_should_be(String expectedFirstName) {
        homePage.clickSetupTab();
        setupPage.clickAccountSection();
        System.out.println("Expected first name is " + expectedFirstName);
        String accountFirstName = accountPage.getAccountFirstName();
        System.out.println("Actual first name is " + accountFirstName);
        Assert.assertEquals("Actual account first name is not as expected account first name!", expectedFirstName, accountFirstName);
    }

    @Then("login should fail with an error {string}")
    public void login_should_fail_with_an_error(String expectedError) {
        System.out.println("Expected error is " + expectedError);
        String actualError = loginPage.getErrTxt();
        System.out.println("Actual error is - " + actualError);
        Assert.assertEquals("Actual error is not as expected error!", expectedError, actualError);
    }

    @Then("login should fail with a {string} error for the {string} field.")
    public void loginShouldFailWithAErrorForTheField(String expectedError, String field) {
        String errorText = loginPage.findErrorText(field);
        System.out.println("Expected error for " + field + " field is - " + expectedError);
        System.out.println("Actual error for " + field + " field is - " + errorText);
        Assert.assertEquals("Actual error for " + field + " field is not as expected!", expectedError, errorText);
    }
}
