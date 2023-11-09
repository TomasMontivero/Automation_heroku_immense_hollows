/*
package com.selenium.heroku.test;

import com.selenium.heroku.pages.CatalogPage;
import net.bytebuddy.asm.Advice;
//import org.junit.jupiter.api.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
public class HerokuTest {

    WebDriver driver;
    CatalogPage catalogPage;
    //@TestFactory

    @Test
    @Order(1)
    public void createItem() {
        System.out.println("-------- @TEST 1");
        catalogPage.uploadNewItemImage();
        catalogPage.setNewItemText();
        catalogPage.clickCreateItem();
        catalogPage.validateItemCreation();
    }

   @Test
    @Order(2)
    public void editItem() {
       System.out.println("-------- @TEST 2");
        catalogPage.editFirstCatalogItem();
        catalogPage.editItemText();
        catalogPage.clickUpdateItem();
        driver.navigate().refresh();
        catalogPage.validateEditedItem();
    }

    @Test
    @Order(3)
    public void deleteItem() {
        System.out.println("-------- @TEST 3");
        catalogPage.deleteItem();
    }

    @Test
    @Order(4)
    public void validateDescriptionMaxLength() {
        System.out.println("-------- @TEST 4");
        catalogPage.validateTextFieldCharactersLimit();
    }

    @Test
    @Order(5)
    public void validateItemVisibility() {
        System.out.println("-------- @TEST 5");
        catalogPage.validateCreatorsItemVisibility();
    }


    //@BeforeEach
    @Before
    public void setupEach() {
        //driver = new ChromeDriver();
        System.out.println("-------- @BEFORE");
        String remoteUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL(remoteUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

        //ChromeOptions chromeOptions = new ChromeOptions();
        //driver = new RemoteWebDriver(desiredCapabilities);

        catalogPage = new CatalogPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        catalogPage.goToCatalog();
    }

    //@AfterEach
    @After
    public void finishEach() {
        System.out.println("-------- @AFTER");
        driver.quit();
    }

}
*/
