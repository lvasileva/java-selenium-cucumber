package example.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends BasePage {

    private static final By TXT_FIRST_NAME_SEL = By.cssSelector("[id=\"first-name\"]");
    private static final By TXT_LAST_NAME_SEL = By.cssSelector("[id=\"last-name\"]");
    private static final By TXT_POSTAL_CODE_SEL = By.cssSelector("[id=\"postal-code\"]");
    private static final By BTN_CONTINUE_SEL = By.cssSelector("[id=\"continue\"]");

    public CheckOutPage() {
        int waitPeriod = 15;

        try {
            waitForElementCondition(ExpectedConditions.presenceOfElementLocated(TXT_FIRST_NAME_SEL), waitPeriod);
        } catch (Exception e) {
            Assert.fail("Portal checkout page page not displayed - timed out after " + waitPeriod + "seconds \n" + e);
        }
    }

    private void setFirstName(String firstName) {

        WebElement txt = driver.findElement(TXT_FIRST_NAME_SEL);
        typeIntoElement(txt, firstName, true);
    }

    private void setLastName(String lastName) {

        WebElement txt = driver.findElement(TXT_LAST_NAME_SEL);
        typeIntoElement(txt, lastName, true);
    }

    private void setPostalCode(String postalCode) {

        WebElement txt = driver.findElement(TXT_POSTAL_CODE_SEL);
        typeIntoElement(txt, postalCode, true);
    }

    private void clickContinue() {

        WebElement btn = driver.findElement(BTN_CONTINUE_SEL);
        btn.click();
    }

    public ConfirmCheckOutPage fillFormAndContinue(String firstName, String lastName, String postalCode) {

        setFirstName(firstName);
        setLastName(lastName);
        setPostalCode(postalCode);
        clickContinue();

        return new ConfirmCheckOutPage();
    }
}
