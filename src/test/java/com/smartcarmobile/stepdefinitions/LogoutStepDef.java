package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.AccountPage;
import com.smartcarmobile.pages.HomePage;
import com.smartcarmobile.pages.LoginPage;
import com.smartcarmobile.pages.SetupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogoutStepDef {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    SetupPage setupPage = new SetupPage();
    AccountPage accountPage = new AccountPage();


    @When("user clicks on the Setup tab")
    public void user_clicks_on_the_setup_tab() {
        homePage.clickSetupTab();
    }

    @And("user clicks on the Account section")
    public void user_clicks_on_the_account_section() {
        setupPage.clickAccountSection();
    }

    @And("user clicks on the Log Out button")
    public void user_clicks_on_the_button() {
        accountPage.scrollToLogOutBtnAndClick();
    }

    @Then("the text {string} should be displayed")
    public void the_text_should_be_displayed(String expectedText) {
        System.out.println("Expected text is " + expectedText);
        String actualText = loginPage.getLoginScreenTxt();
        System.out.println("Actual text is " + actualText);
        Assert.assertEquals("Login screen text is not as expected text!", expectedText, actualText);
    }
}
