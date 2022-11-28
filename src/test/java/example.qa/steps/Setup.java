package example.qa.steps;

import example.qa.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Setup {

    @Before
    public static void Before() {


        WebDriverManager.chromedriver().setup();
        ChromeOptions browserOptions = new ChromeOptions();
        Context.setDriver(new ChromeDriver(browserOptions));
    }

    @After
    public static void After() {

        if (Context.getDriver() != null) {
            Context.getDriver().quit();
        }
    }
}
