package com.smartcarmobile.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import com.smartcarmobile.utilities.DriverManager;
import com.smartcarmobile.utilities.GlobalParams;
import com.smartcarmobile.utilities.ServerManager;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                  "html:target/cucumber-report.html",
                  "json:target/cucumber-report.json",
                  "summary"
        },
        features = "src/test/resources/features",
        glue = "com/smartcarmobile/stepdefinitions",
        dryRun = false,
        tags = "@wip"
)

public class CuckesRunnerTest {

        @BeforeClass
        public static void initialize() throws Exception {
                GlobalParams params = new GlobalParams();
                params.initializeGlobalParams();

                ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_"
                        + params.getDeviceName());

                new ServerManager().startServer();
                new DriverManager().initializeDriver();
        }


        @AfterClass
        public static void quit() {
                DriverManager driverManager = new DriverManager();
                if (driverManager.getDriver() != null) {
                        driverManager.getDriver().quit();
                        driverManager.setDriver(null);
                }
                ServerManager serverManager = new ServerManager();
                if (serverManager.getServer() != null) {
                        serverManager.getServer().stop();
                }
        }

}
