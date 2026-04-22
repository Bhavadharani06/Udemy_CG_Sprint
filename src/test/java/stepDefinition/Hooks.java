package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utility.Base;
import utility.HandleCookies;
import utility.Pages;

public class Hooks {

    public static Properties prop;

    // 🔹 Load config
    public void loadConfig() {
        prop = new Properties();
        try {
            String path = System.getProperty("user.dir") + "/src/main/resources/CommonData/config.properties";
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup(io.cucumber.java.Scenario scenario) {
        loadConfig();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        Base.driver = new ChromeDriver(options);
        Base.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Base.driver.get(prop.getProperty("url"));
        Pages.initPages(Base.driver);

        System.out.println("Browser launched");

        // 🔹 Skip cookies for signup scenarios
        if (scenario.getSourceTagNames().contains("signup")) {
            System.out.println("👉 Signup scenario - skipping cookies");
            return;
        }

        // 🔹 Normal flow
        HandleCookies cookieUtil = new HandleCookies();
        String cookieFile = "cookies1.data";

        boolean loaded = cookieUtil.loadCookies(Base.driver, cookieFile);

        if (!loaded || !Pages.homePage.isUserLoggedIn()) {
            System.out.println("👉 Please login manually...");
            try {
                Thread.sleep(50000); // give time for manual login
            } catch (Exception e) {}

            if (Pages.homePage.isUserLoggedIn()) {
                cookieUtil.saveCookies(Base.driver, cookieFile);
            } else {
                throw new RuntimeException("Login required!");
            }
        } else {
            // Navigate to a protected page after loading cookies
            Base.driver.get("https://www.udemy.com/home/my-courses");
        }
    }

    @After
    public void tearDown() {
        if (Base.driver != null) {
            Base.driver.quit();
        }
        System.out.println("Browser closed");
    }
}
