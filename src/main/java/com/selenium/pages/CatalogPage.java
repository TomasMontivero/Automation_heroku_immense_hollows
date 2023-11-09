package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CatalogPage extends BasePage{

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    By imageUploadButton = By.id("inputImage");
    By textAreaInput = By.name("text");
    By createItemButton = By.className("btn-success");
    By catalogItemText = By.xpath("(//*[@class=\"media-body\"]//div//p)[1]");
    By catalogItemEditButton = By.xpath("(//*[@class=\"media-body\"]//button[text()='Edit'])[1]");
    By updateItemButton = By.xpath("//button[text()='Update Item']");
    By deleteItemButton = By.xpath("//*[contains(text(), '" + inputs.getProperty("item_description") + "')]/parent::div/parent::div//button[text()='Delete']");
    By deleteItemConfirmationButton = By.xpath("//button[text()='Yes, delete it!']");
    By createdItems = By.xpath("//*[contains(text(), '" + inputs.getProperty("item_description") + "')]");

    int itemId;


    public void goToCatalog() {
        driver.get(urls.getProperty("catalog"));
    }

    public void uploadNewItemImage() {
        File file = new File(inputs.getProperty("item_image_path"));
        sendKeys(imageUploadButton, file.getAbsolutePath());
    }

    public void setNewItemText() {
        itemId = (int) (Math.random()*10000);
        sendKeys(textAreaInput, inputs.getProperty("item_description") + itemId);
    }

    public void clickCreateItem() {
        click(createItemButton);
    }

    public void clickUpdateItem() {
        click(updateItemButton);
    }

    public void editFirstCatalogItem() {
        click(catalogItemEditButton);
    }

    public void editItemText() {
        String originalItemText = webElement(catalogItemText).getText();
        String newItemText = originalItemText + " (edited)";
        sendKeys(textAreaInput, newItemText);
    }

    public void deleteItem() {
        int createdItemsBeforeDelete = getElementsAmount(createdItems);
        click(deleteItemButton);
        click(deleteItemConfirmationButton);
        driver.navigate().refresh();
        int createdItemsAfterDelete = getElementsAmount(createdItems);
        assertTrue(createdItemsBeforeDelete > createdItemsAfterDelete);
    }

    public void validateItemCreation() {
        assertTrue(isPresent(By.xpath("//*[contains(text(), '" + inputs.getProperty("item_description") + itemId  + "')]")));
    }

    public void validateEditedItem() {
        assertTrue(webElement(catalogItemText).getText().contains(" (edited)"));
    }

    public void validateTextFieldCharactersLimit() {
        sendKeys(textAreaInput, inputs.getProperty("item_description_299_characters"));
        assertTrue(webElement(createItemButton).isEnabled());
        sendKeys(textAreaInput, inputs.getProperty("item_description_300_characters"));
        assertTrue(webElement(createItemButton).isEnabled());
        sendKeys(textAreaInput, inputs.getProperty("item_description_301_characters"));
        assertFalse(webElement(createItemButton).isEnabled());
    }

    public void validateCreatorsItemVisibility() {
        assertTrue(isPresent(By.xpath("//*[contains(text(), '" + inputs.getProperty("creators_item_description") + "')]")));
    }


}
