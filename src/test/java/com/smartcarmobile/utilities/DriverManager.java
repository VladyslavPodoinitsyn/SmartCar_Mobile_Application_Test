package com.smartcarmobile.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DriverManager {

    private static Connection connection;
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public static Connection createDatabaseConnection() throws ClassNotFoundException, SQLException {

        String url = PropertyManager.getProperty("DB_URL");
        String username = PropertyManager.getProperty("DB_USERNAME");
        String password = PropertyManager.getProperty("DB_PASSWORD");

        Class.forName("org.postgresql.Driver");

        connection = java.sql.DriverManager.getConnection(url, username, password);

        System.out.println("Database connection established successfully!");

        return connection;
    }

    public static void destroyDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();

        try {
            utils.log().info("initializing Appium driver");
            switch (params.getPlatformName()) {
                case "android":
                    driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                    break;
                case "ios":
                    // Ensure the directory for the log file exists
                    File logDir = new File("iOS_iPhone 14 Pro Max");
                    if (!logDir.exists()) {
                        logDir.mkdirs();
                    }
                    driver = new IOSDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                    break;
            }
            if (driver == null) {
                throw new Exception("driver is null. ABORT!!!");
            }
            utils.log().info("Driver is initialized");
            DriverManager.driver.set(driver);
        } catch (IOException e) {
            e.printStackTrace();
            utils.log().fatal("Driver initialization failure. ABORT !!!!{}", e.toString());
            throw e;
        }
    }
}
