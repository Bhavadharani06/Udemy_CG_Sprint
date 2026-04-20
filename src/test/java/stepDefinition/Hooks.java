package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utility.Base;
import utility.Pages;

public class Hooks {

    public static WebDriver driver;
    public static Properties prop;

    // Load config
    public void loadConfig() {

        prop = new Properties();

        try {
            String path = System.getProperty("user.dir")
                    + "/src/main/resources/CommonData/config.properties";

            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {

        loadConfig();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("user-data-dir=C:\\Users\\Swaathihaa.T.T\\AppData\\Local\\Google\\Chrome\\User Data - Copy");
        options.addArguments("profile-directory=Default");

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        
        Base.driver = driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("url"));

        Pages.initPages(driver);

        System.out.println("Browser launched");
    }
    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        System.out.println("Browser closed");
    }
}