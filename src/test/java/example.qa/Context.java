package example.qa;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 */
public class Context {

    private static RemoteWebDriver driver;

    public static RemoteWebDriver getDriver() {

        return driver;
    }

    public static void setDriver(final RemoteWebDriver driver) {

        Context.driver = driver;
    }
}
