package example.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    private static final By TXT_USERNAME_SEL = By.cssSelector("[id=\"user-name\"]");

    private static final By TXT_PASSWORD_SEL = By.cssSelector("[id=\"password\"]");

    private static final By BTN_SUBMIT_SEL = By.cssSelector("[id=\"login-button\"]");

    public LoginPage() {

        int waitPeriod = 15;

        try {
            waitForElementCondition(ExpectedConditions.presenceOfElementLocated(BTN_SUBMIT_SEL), waitPeriod);
        } catch (Exception e) {
            Assert.fail("Portal login page not displayed - timed out after " + waitPeriod + "seconds \n" + e);
        }
    }

    private void setUsername(String username){

        WebElement txt = driver.findElement(TXT_USERNAME_SEL);
        typeIntoElement(txt, username, true);
    }

    private void setPassword(String password){

        WebElement txt = driver.findElement(TXT_PASSWORD_SEL);
        typeIntoElement(txt, password, true);
    }

    private void clickSubmit() {

        WebElement btn = driver.findElement(BTN_SUBMIT_SEL);
        btn.click();
    }

    public HomePage login(String username, String password){

        setUsername(username);
        setPassword(password);
        clickSubmit();

        return new HomePage();
    }
}
