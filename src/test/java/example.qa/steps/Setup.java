package example.qa.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.qa.Context;
import example.qa.data.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class Setup {

    @Before
    public static void Before() {


        WebDriverManager.chromedriver().setup();
        ChromeOptions browserOptions = new ChromeOptions();
        Context.setDriver(new ChromeDriver(browserOptions));
    }

    @Before
    public static void GetUsersFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Context.setUsers(objectMapper.readValue(Paths.get("src/test/resources/user.json").toFile(), new TypeReference<List<User>>() {
        }));
    }

    @After
    public static void After() {

        if (Context.getDriver() != null) {
            Context.getDriver().quit();
        }
    }
}
