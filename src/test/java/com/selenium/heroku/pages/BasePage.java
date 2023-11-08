package com.selenium.heroku.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    Properties urls = new Properties();
    Properties inputs = new Properties();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            urls.load(new FileInputStream("src/test/resources/properties/urls.properties"));
            inputs.load(new FileInputStream("src/test/resources/properties/inputs.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement webElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean isPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void click(By locator) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        return webElement(locator).getText();
    }


}
