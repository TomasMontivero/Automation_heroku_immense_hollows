package com.selenium.heroku.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogPage extends BasePage{

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    By imageUploadButton = By.id("inputImage");
    By textAreaInput = By.name("text");
    By submitButton = By.className("btn-success");
    int itemId;


    public void goToCatalog() {
        driver.get(urls.getProperty("catalog"));
    }

    public void uploadImage() {
        File file = new File(inputs.getProperty("item_image_path"));
        sendKeys(imageUploadButton, file.getAbsolutePath());
    }

    public void setDescription() {
        itemId = (int) (Math.random()*10000);
        sendKeys(textAreaInput, inputs.getProperty("item_description") + itemId);
    }

    public void clickCreateItem() {
        click(submitButton);
    }

    public void validateItemCreation() {
        assertTrue(isPresent(By.xpath("//*[contains(text(), '" + inputs.getProperty("item_description") + itemId  + "')]")));
    }

}
