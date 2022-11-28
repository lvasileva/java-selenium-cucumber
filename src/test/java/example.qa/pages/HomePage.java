package example.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    private static final By BTN_SHOPPING_CART_SEL = By.cssSelector("[id=\"shopping_cart_container\"]");
    private static final By TXT_SHOPPING_CART_BADGE_SEL = By.cssSelector(".shopping_cart_badge");
    private static final By ITEM_LIST_SEL = By.cssSelector(".inventory_list");
    private static final By ITEM_SEL = By.cssSelector(".inventory_item");
    private static final By TXT_ITEM_NAME_SEL = By.cssSelector(".inventory_item_name");
    private static final By BTN_ITEM_ADD_TO_CART_SEL = By.cssSelector("[id^=\"add-to-cart\"]");

    public HomePage() {
        int waitPeriod = 15;

        try {
            waitForElementCondition(ExpectedConditions.presenceOfElementLocated(ITEM_LIST_SEL), waitPeriod);
        } catch (Exception e) {
            Assert.fail("Portal home page not displayed - timed out after " + waitPeriod + "seconds \n" + e);
        }
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

    public void addItemToShoppingCartByName(String name) {
        boolean found = false;
        List<WebElement> list = getAllItems();
        for (WebElement ele : list) {
            if (ele.findElement(TXT_ITEM_NAME_SEL).getText().equals(name)) {
                found = true;
                ele.findElement(BTN_ITEM_ADD_TO_CART_SEL).click();
            }
        }
        if (!found) Assert.fail("Cannot find the Item with provided name " + name);
    }

    public void addSeveralItemsToShoppingCartByName(List<String> names) {
        for (String name : names) {
            addItemToShoppingCartByName(name);
        }
    }

    public boolean IsListOfItemsPresented() {
        return driver.findElement(ITEM_LIST_SEL).isDisplayed();

    }

    public CartPage openShoppingCart() {
        driver.findElement(BTN_SHOPPING_CART_SEL).click();
        return new CartPage();
    }

    public int getNumberOfItemsInTheShoppingCart() {

        return Integer.parseInt(driver.findElement(BTN_SHOPPING_CART_SEL).findElement(TXT_SHOPPING_CART_BADGE_SEL).getText());
    }

}
