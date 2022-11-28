package example.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static final By BTN_CHECKOUT_SEL = By.cssSelector("[data-test=\"checkout\"]");
    private static final By ITEM_LIST_SEL = By.cssSelector(".cart_list");
    private static final By ITEM_SEL = By.cssSelector(".cart_item");
    private static final By TXT_ITEM_NAME_SEL = By.cssSelector(".inventory_item_name");
    private static final By BTN_ITEM_REMOVE_SEL = By.cssSelector("[id^=\"remove\"]");

    public CartPage() {
        int waitPeriod = 15;

        try {
            waitForElementCondition(ExpectedConditions.presenceOfElementLocated(ITEM_LIST_SEL), waitPeriod);
        } catch (Exception e) {
            Assert.fail("Portal cart page not displayed - timed out after " + waitPeriod + "seconds \n" + e);
        }
    }

    public CheckOutPage checkOut() {
        driver.findElement(BTN_CHECKOUT_SEL).click();
        return new CheckOutPage();
    }

    public List<WebElement> getAllItems() {

        return driver.findElements(ITEM_SEL);
    }

    public List<String> getAllItemNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> list = getAllItems();
        for (WebElement ele : list) {
            names.add(ele.findElement(TXT_ITEM_NAME_SEL).getText());
        }
        return names;
    }

    public List<String> getAllNamesBySubstring(String subtring) {
        List<String> names = new ArrayList<>();
        List<WebElement> list = getAllItems();
        for (WebElement ele : list) {
            if (ele.findElement(TXT_ITEM_NAME_SEL).getText().contains(subtring)) {
                names.add(ele.findElement(TXT_ITEM_NAME_SEL).getText());
            }
        }
        return names;
    }

    public void removeItemFromShoppingCartByName(String name) {
        boolean found = false;
        List<WebElement> list = getAllItems();
        for (WebElement ele : list) {
            if (ele.findElement(TXT_ITEM_NAME_SEL).getText().equals(name)) {
                found = true;
                ele.findElement(BTN_ITEM_REMOVE_SEL).click();
            }
        }
        if (!found) Assert.fail("Cannot find the Item with provided name " + name);
    }

    public void removeSeveralItemsFromShoppingCartByName(List<String> names) {
        for (String name : names) {
            removeItemFromShoppingCartByName(name);
        }
    }
}
