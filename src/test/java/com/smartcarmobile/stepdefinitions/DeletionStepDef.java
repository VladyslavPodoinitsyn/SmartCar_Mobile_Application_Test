package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.*;
import com.smartcarmobile.utilities.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class DeletionStepDef {

    TestUtils utils = new TestUtils();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    SetupPage setupPage = new SetupPage();
    AccountPage accountPage = new AccountPage();

    @When("user login to the application")
    public void user_login_to_the_application_using() throws IOException, InterruptedException {
        utils.sharedUserAccountCreation("car_user",00001,"full_access","+12345678999","Test","Test","000000","US", "Washington","Seattle","1234 Ave");
        loginPage.sharedUserAccountLogin();
    }

    @And("user clicks the Setup tab")
    public void user_clicks_the_setup_tab() {
        homePage.acceptNotifications();
        homePage.clickSetupTab();
    }

    @And("user clicks the Account section")
    public void user_clicks_the_account_section() {
        setupPage.clickAccountSection();
    }

    @And("user clicks the Account Deletion section")
    public void user_clicks_the_account_deletion_section() {
        accountPage.scrollToAccountDeletionSectionAndClick();
    }

    @When("user clicks the Delete Account button")
    public void user_clicks_the_delete_account_button() {
        accountPage.clickDeleteAccountBtn();
    }

    @And("user clicks the Confirm button")
    public void user_clicks_the_confirm_button() {
        accountPage.clickDeleteAccountConfirmBtn();
    }

    @And("user clicks the Okay button")
    public void user_clicks_the_okay_button() {
        loginPage.clickAccountDeletionOkBtn();
    }

    @And("user enters username and password")
    public void user_enters_username_and_password() {
        loginPage.sharedUserAccountLogin();
    }

    @Then("login should fail with the {string} error")
    public void login_should_fail_with_the_error(String expectedError) {
        System.out.println("Expected error is - " + expectedError);
        String actualError = loginPage.getErrTxt();
        System.out.println("Actual error is - " + actualError);
        Assert.assertEquals("Actual error is not as expected error!", expectedError, actualError);
    }
}
