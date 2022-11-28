package example.qa.pages;

import example.qa.Context;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    RemoteWebDriver driver;
    public BasePage(){
        driver = Context.getDriver();
    }

    public void typeIntoElement(WebElement element, String typeContents, boolean replaceContents) {
        element.click();
        if (replaceContents) {
            element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "");
            element.clear();
        }

        element.sendKeys(typeContents);

    }

    public <V> V waitForElementCondition(ExpectedCondition<V> condition, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout));
        Objects.requireNonNull(condition);
        return webDriverWait.until(condition);
    }

    public void open(String url) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long)3));
        this.driver.navigate().to(url);
    }

    public boolean waitForPageToBeLoaded(int maxWaitMillis, int pollDelimiter) {

        int currentLoop = 1;
        double startTime = System.currentTimeMillis();
        boolean stable = false;

        while (System.currentTimeMillis() < startTime + maxWaitMillis) {

            String prevState = getCurrentPageSource();

            try {
                System.out.println("Waiting for DOM to complete load : pausing for " + pollDelimiter + "ms, and total wait period of " + maxWaitMillis +
                        "ms  : current loop : " + currentLoop);
                currentLoop++;
                Thread.sleep(pollDelimiter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // <-- would need to wrap : a try catch
            String currentState = getCurrentPageSource();

            if (prevState.equals(currentState)) {
                stable = true;
                return stable;
            }
        }

        //if we reach this point without the DOM going stable, then ideally we should report this and fail out
        System.out.println("Waiting for DOM to complete load timed out: paused for " + pollDelimiter + "ms, and total wait period of " + maxWaitMillis + "ms");
        return stable;
    }


    private String getCurrentPageSource()  {

        String pageSource = null;
        int maxLoop = 10;
        while(pageSource==null && maxLoop-->0){

            try {
                pageSource = driver.getPageSource();
            } catch (Exception e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }

        return pageSource;
    }

    public void scrollIntoView(WebElement element) {

        driver.executeScript("scroll(0,0)");

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("Trying to scroll element [" + element + "] into view, but element could not be found.   This could be a slow system symptom.... \n" + e);
        }
    }

}
