package com.smartcarmobile.utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class ServerManager {

    private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    public void startServer() {
        utils.log().info("starting appium server");
        AppiumDriverLocalService server = getAppiumServer();
        server.start();
        if (!server.isRunning()) {
            utils.log().fatal("Appium server not started. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        server.clearOutPutStreams();
        ServerManager.server.set(server);
        utils.log().info("Appium server started");
    }

    public AppiumDriverLocalService getAppiumServer() {
        GlobalParams params = new GlobalParams();

        // Construct the directory path for the log file
        String logDirPath = params.getPlatformName() + "_" + params.getDeviceName();
        File logDir = new File(logDirPath);

        // Ensure the directory exists
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(() -> "--base-path", "/wd/hub")
                .withLogFile(new File(params.getPlatformName() + "_"
                        + params.getDeviceName() + File.separator + "Server.log"))
                .build();

        // Directly invoking the command to start the server
        try {
            service.start();
        } catch (Exception e) {
            utils.log().fatal("Failed to start Appium server. {}", e.getMessage());
            throw new RuntimeException("Failed to start Appium server.", e);
        }
        return service;
    }
}
