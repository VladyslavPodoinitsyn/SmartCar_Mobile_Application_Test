package com.smartcarmobile.stepdefinitions;

import com.smartcarmobile.pages.BasePage;
import com.smartcarmobile.utilities.DriverManager;
import com.smartcarmobile.utilities.VideoManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {

    @Before
    public void initialize() throws Exception {
        BasePage basePage = new BasePage();
        basePage.closeApp();
        basePage.launchApp();
        new VideoManager().startRecording();
    }

    @After
    public void tearDownScenario(Scenario scenario) throws IOException {

        //IF MY SCENARIO FAILS TAKE A SCREENSHOT
        if (scenario.isFailed()) {

            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        new VideoManager().stopRecording(scenario.getName());

    }
}
