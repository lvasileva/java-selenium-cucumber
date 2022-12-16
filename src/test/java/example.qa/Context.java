package example.qa;

import example.qa.data.User;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

/**
 *
 */
public class Context {

    private static RemoteWebDriver driver;

    private static List<User> users;

    public static RemoteWebDriver getDriver() {

        return driver;
    }

    public static void setDriver(final RemoteWebDriver driver) {

        Context.driver = driver;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(final List<User> users) {
        Context.users = users;
    }
}
