package com.selenium.heroku.test;

import com.selenium.heroku.pages.CatalogPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringRunner.class)
//@SpringJUnitConfig
//@Configuration
//@ContextConfiguration(locations={"applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleTest.class)
public class HerokuTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    WebDriver driver;
    CatalogPage catalogPage;
    //@TestFactory

    /*@Autowired
    private ApplicationContext applicationContext;*/

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
        //String remoteUrl = "http://localhost:4444/wd/hub";    //TODO: Uncomment to run locally
        String remoteUrl = "http://selenium-hub:4444/wd/hub";         //TODO: Uncomment to run through docker
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
