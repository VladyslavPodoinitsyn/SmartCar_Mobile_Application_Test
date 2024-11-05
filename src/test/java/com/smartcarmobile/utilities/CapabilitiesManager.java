package com.smartcarmobile.utilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class CapabilitiesManager {

    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCaps() throws IOException {

        GlobalParams params = new GlobalParams();

        try{
            utils.log().info("getting capabilities");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", params.getPlatformName());
            desiredCapabilities.setCapability("udid", params.getUDID());
            desiredCapabilities.setCapability("deviceName", params.getDeviceName());

            switch(params.getPlatformName()){
                case "android":
                    desiredCapabilities.setCapability("automationName", PropertyManager.getProperty("androidAutomationName"));
                    desiredCapabilities.setCapability("systemPort", params.getSystemPort());
                    desiredCapabilities.setCapability("appium:chromeDriverPort", params.getChromeDriverPort());
                    desiredCapabilities.setCapability("avd", "Pixel_4");
                    desiredCapabilities.setCapability("avdLaunchTimeout", 250000);
                    desiredCapabilities.setCapability("newCommandTimeout", 500);
                    desiredCapabilities.setCapability("platformVersion", "14.0");
                    desiredCapabilities.setCapability("app", "/Users/vladp/IdeaProjects/SmartCar-Native-Apps-Automation/src/test/resources/apps/app.apk");
                    break;

                case "ios":
                    desiredCapabilities.setCapability("automationName", PropertyManager.getProperty("iOSAutomationName"));
                    desiredCapabilities.setCapability("deviceName", "iPhone 14 Pro Max");
                    desiredCapabilities.setCapability("udid", "8E81-BEA95C654EAB");
                    desiredCapabilities.setCapability("platformVersion", "17.5");
                    desiredCapabilities.setCapability("simulatorStartupTimeout", 200000);
                    desiredCapabilities.setCapability("bundleId", PropertyManager.getProperty("iOSBundleId"));
                    desiredCapabilities.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    desiredCapabilities.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                    desiredCapabilities.setCapability("app", "/Users/vladp/IdeaProjects/SmartCar-Native-Apps-Automation/src/test/resources/apps/SmartCarmobile.app");
                    break;
            }
            return desiredCapabilities;
        } catch(Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!{}", e.toString());
            throw e;
        }
    }
}
