package com.selenium.test;

import com.selenium.pages.CatalogPage;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleTest.class)
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class HerokuTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    WebDriver driver;
    CatalogPage catalogPage;
    Logger logger = Logger.getLogger("HerokuTest");

    @Test
    @Tag("Smoke")
    public void test_1_createItem() {
        logger.info("TEST 1: Create item");
        catalogPage.uploadNewItemImage();
        catalogPage.setNewItemText();
        catalogPage.clickCreateItem();
        catalogPage.validateItemCreation();
    }

   @Test
   @Tag("Smoke")
    public void test_2_editItem() {
        logger.info("TEST 2: Edit item");
        catalogPage.editFirstCatalogItem();
        catalogPage.editItemText();
        catalogPage.clickUpdateItem();
        driver.navigate().refresh();
        catalogPage.validateEditedItem();
    }

    @Test
    @Tag("Smoke")
    public void test_3_deleteItem() {
        logger.info("TEST 3: Delete item");
        catalogPage.deleteItem();
    }

    @Test
    @Tag("Smoke")
    public void test_4_validateDescriptionMaxLength() {
        logger.info("TEST 4: Validate text field char limit");
        catalogPage.validateTextFieldCharactersLimit();
    }

    @Test
    @Tag("Smoke")
    public void test_5_validateItemVisibility() {
        logger.info("TEST 5: Validate item visibility");
        catalogPage.validateCreatorsItemVisibility();
    }


    @Before
    public void setupEach() {
        //driver = new ChromeDriver();
        //String remoteUrl = "http://localhost:4444/wd/hub";    //TODO: Uncomment to run locally
        String remoteUrl = "http://selenium-hub:4444/wd/hub";   //TODO: Uncomment to run through docker
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL(remoteUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        catalogPage = new CatalogPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        catalogPage.goToCatalog();
    }

    @After
    public void finishEach() {
        driver.quit();
    }

}
