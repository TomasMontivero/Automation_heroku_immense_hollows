package com.selenium.heroku.test;

import com.selenium.heroku.pages.CatalogPage;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HerokuTest {

    WebDriver driver;
    CatalogPage catalogPage;

    @Test
    @Tag("smoke")
    public void sampleTest() {
        catalogPage.goToCatalog();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        catalogPage = new CatalogPage(driver);
    }

    @AfterEach
    public void finishEach() {
        driver.quit();
    }

}
