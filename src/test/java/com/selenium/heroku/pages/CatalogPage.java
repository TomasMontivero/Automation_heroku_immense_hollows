package com.selenium.heroku.pages;

import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage{
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public void goToCatalog() {
        driver.get(urls.getProperty("catalog"));
    }

}
