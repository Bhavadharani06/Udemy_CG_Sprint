package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utility.Base;
import utility.HandleCookies;

import utility.ExtentReportManager;
import utility.Pages;
import utility.ScreenshotUtil;

public class Hooks {

    public static Properties prop;


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

        Base.setDriver(new ChromeDriver(options));

        Base.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Base.getDriver().get(prop.getProperty("url"));
        Pages.initPages(Base.getDriver());

        System.out.println("Browser launched");

        // 🔹 Skip cookies for signup scenarios
        if (scenario.getSourceTagNames().contains("signup")) {
            System.out.println("👉 Signup scenario - skipping cookies");
            return;
        }

        // 🔹 Normal flow
        HandleCookies cookieUtil = new HandleCookies();
        String cookieFile = "cookies1.data";

        boolean loaded = cookieUtil.loadCookies(Base.getDriver(), cookieFile);

        if (!loaded || !Pages.get().homePage.isUserLoggedIn()) {
            System.out.println("👉 Please login manually...");
            try {
                Thread.sleep(50000); // give time for manual login
            } catch (Exception e) {}

            if (Pages.get().homePage.isUserLoggedIn()) {
                cookieUtil.saveCookies(Base.getDriver(), cookieFile);
            } else {
                throw new RuntimeException("Login required!");
            }
        } else {
            // Navigate to a protected page after loading cookies
            Base.getDriver().get("https://www.udemy.com/home/");
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        ExtentTest test = ExtentReportManager.getTest();

        if (scenario.isFailed()) {
            String base64Screenshot = ScreenshotUtil.takeScreenshotAsBase64(Base.getDriver());

            test.fail("Scenario FAILED: " + scenario.getName(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) Base.getDriver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Failure Screenshot");

        } else {
            test.pass("Scenario PASSED: " + scenario.getName());
        }

        if (Base.getDriver() != null) {
            Base.getDriver().quit();
            Base.unload();
            Pages.remove();
        }

        System.out.println("Browser closed: " + Thread.currentThread().getId());
    }

    @AfterAll
    public static void afterAll() {
        ExtentReportManager.flushReports();
    }
}