package com.selenium.heroku.test;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HerokuTest {

    WebDriver driver;

    @Test
    @Tag("smoke")
    public void sampleTest() {

    }

    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        driver.get("http://immense-hollows-74271.herokuapp.com/");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @AfterEach
    public void finishEach() {
        driver.quit();
    }

}
