package com.smartcarmobile.pages;

import com.smartcarmobile.utilities.GlobalParams;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.smartcarmobile.utilities.DriverManager;
import io.appium.java_client.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class BasePage {

    public static AppiumDriver driver;

    public BasePage(){
        driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void closeApp() {
        switch(new GlobalParams().getPlatformName()){
            case "android":
                ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().
                        getCapability("appPackage").toString());
                break;
            case "ios":
                ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().
                        getCapability("bundleId").toString());
        }
    }

    public void launchApp() {
        switch(new GlobalParams().getPlatformName()){
            case "android":
                ((InteractsWithApps) driver).activateApp(driver.getCapabilities().
                        getCapability("appPackage").toString());
                break;
            case "ios":
                ((InteractsWithApps) driver).activateApp(driver.getCapabilities().
                        getCapability("bundleId").toString());
        }
    }

    public String getText(WebElement e) {
        String txt;
        switch(new GlobalParams().getPlatformName()){
            case "android":
                txt = getAttribute(e, "text");
                break;
            case "ios":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
        return txt;
    }

    private String getAttribute(WebElement e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    public void scrollToElementAndClick(WebElement element) {
        WebElement targetElement = findElementWithScroll(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(targetElement));
        waitAndClickElement(targetElement);
    }

    public void scrollToElement(WebElement element) {
        WebElement targetElement = findElementWithScroll(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(targetElement));
    }

    private WebElement findElementWithScroll(WebElement element) {
        while (true) {
            try {
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                // Element not found or not visible, continue to scroll
            }

            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            try {
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence swipe = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Collections.singletonList(swipe));
            } catch (Exception ex) {
                System.err.println("Error performing swipe action: " + ex.getMessage());
            }
        }
    }

    public void selectPickerWheelValue(String desiredCode) {
        WebElement pickerWheel = driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypePickerWheel'"));
        pickerWheel.sendKeys(desiredCode);
    }

    public void waitUntilElementPresentAndVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException | TimeoutException e) {
        }
    }

    public void waitAndClickElement(WebElement element) {
        if (element != null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }



    public void scrollOrSwipe(String direction, int distance) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY, endY;

        if (direction.equalsIgnoreCase("up")) {
            startY = (int) (size.height * 0.8);
            endY = startY - distance;
            if (endY < 0) endY = 0;
        } else if (direction.equalsIgnoreCase("down")) {
            startY = (int) (size.height * 0.2);
            endY = startY + distance;
            if (endY > size.height) endY = size.height;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        if (driver instanceof AppiumDriver) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            (driver).perform(Arrays.asList(swipe));
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + driver.getClass().getName());
        }
    }

}