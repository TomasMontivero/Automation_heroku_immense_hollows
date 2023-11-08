package com.selenium.heroku.test;

import com.selenium.heroku.pages.CatalogPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HerokuTest {

    WebDriver driver;
    CatalogPage catalogPage;


    @Test
    @Order(1)
    public void createItem() {
        catalogPage.uploadNewItemImage();
        catalogPage.setNewItemText();
        catalogPage.clickCreateItem();
        catalogPage.validateItemCreation();
    }

   @Test
    @Order(2)
    public void editItem() {
        catalogPage.editFirstCatalogItem();
        catalogPage.editItemText();
        catalogPage.clickUpdateItem();
        driver.navigate().refresh();
        catalogPage.validateEditedItem();
    }

    @Test
    @Order(3)
    public void deleteItem() {
        catalogPage.deleteItem();
    }

    @Test
    @Order(4)
    public void validateDescriptionMaxLength() {
        catalogPage.validateTextFieldCharactersLimit();
    }

    @Test
    @Order(5)
    public void validateItemVisibility() {
        catalogPage.validateCreatorsItemVisibility();
    }


    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        catalogPage = new CatalogPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        catalogPage.goToCatalog();
    }

    @AfterEach
    public void finishEach() {
        driver.quit();
    }

}
