package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.HomePage;
import com.smartcarmobile.utilities.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;

public class CellularUsageStepDef {

    HomePage homePage = new HomePage();

    @When("user refreshes the Home screen")
    public void user_refreshes_the_home_screen() throws SQLException, ClassNotFoundException {
        TestUtils.storeOriginalCellularUsage();
        homePage.refreshPage();
    }

    @Then("data usage alert {string} should appear with the text {string}")
    public void data_usage_alert_should_appear_with_the_text(String value, String expectedAlertText) throws SQLException, ClassNotFoundException {
        TestUtils.updateDeviceCellularUsage(value);
        homePage.refreshPage();
        System.out.println("Expected alert text is " + expectedAlertText);
        String actualAlertText = homePage.getDataUsageText();
        System.out.println("Actual alert text is " + actualAlertText);
        TestUtils.restoreOriginalCellularUsage();
        Assert.assertEquals("Actual alert text is not as expected alert text!", expectedAlertText, actualAlertText);
    }

    @And("user selects LTE sours in the Dash Cam widget")
    public void user_selects_lte_sours_in_the_dash_cam_widget() throws SQLException, ClassNotFoundException {
        TestUtils.updateDeviceCellularUsage("100");
        TestUtils.waitFor(2000);
        homePage.refreshPage();
        homePage.scrollToDashCamWidget();
    }

    @And("user clicks on the label in the Dash Cam widget")
    public void user_clicks_on_the_label_in_the_dash_cam_widget() {
        homePage.startLiveStream("LTE");
    }

    @Then("Live View-Not enough data screen should appear with text contains {string}")
    public void live_view_not_enough_data_screen_should_appear_with_text_contains(String expectedErrorText) throws SQLException, ClassNotFoundException {
        System.out.println("Expected error text should contain - " + expectedErrorText);
        String actualErrorText = homePage.liveViewNotEnoughData.getText();
        System.out.println("Actual error text is " + actualErrorText);
        TestUtils.restoreOriginalCellularUsage();
        Assert.assertTrue("Actual error text does not contain the expected text!", actualErrorText.contains(expectedErrorText));
    }
}
