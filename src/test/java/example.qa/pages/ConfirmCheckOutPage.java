package example.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmCheckOutPage extends BasePage {

    private static final By TXT_TOTAL_SEL = By.cssSelector(".summary_total_label");

    public ConfirmCheckOutPage() {
        int waitPeriod = 15;

        try {
            waitForElementCondition(ExpectedConditions.presenceOfElementLocated(TXT_TOTAL_SEL), waitPeriod);
        } catch (Exception e) {
            Assert.fail("Portal confirm checkout page not displayed - timed out after " + waitPeriod + "seconds \n" + e);
        }
    }

    public float getTotalValue() {
        return Float.parseFloat(driver.findElement(TXT_TOTAL_SEL).getText().replaceAll("[^\\d.]", ""));
    }

}
