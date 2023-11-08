package com.selenium.heroku.test;

import com.selenium.heroku.pages.CatalogPage;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HerokuTest {

    WebDriver driver;
    CatalogPage catalogPage;


    @Test
    @Order(1)
    public void createItem() {
        catalogPage.goToCatalog();
        catalogPage.uploadImage();
        catalogPage.setDescription();
        catalogPage.clickCreateItem();
        catalogPage.validateItemCreation();
    }

/*    @Test
    @Order(2)
    public void editItem() {
        //
    }

    @Test
    @Order(3)
    public void deleteItem() {
        //
    }

    @Test
    @Order(4)
    public void validateDescriptionMaxLength() {
        //
    }

    @Test
    @Order(5)
    public void validateItemVisibility() {
        //
    }*/


    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        catalogPage = new CatalogPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    public void finishEach() {
        driver.quit();
    }

}
