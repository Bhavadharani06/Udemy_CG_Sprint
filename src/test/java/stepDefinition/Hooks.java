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
import utility.ExtentReportManager;
import utility.Pages;
import utility.ScreenshotUtil;

public class Hooks {

    public static Properties prop;

    // Load config file
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
    public void setup(Scenario scenario) {

        loadConfig();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // Optional (only for debugging)
        options.setExperimentalOption("detach", true);

        WebDriver driver = new ChromeDriver(options);

        // Thread-safe driver
        Base.setDriver(driver);

        Base.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Base.getDriver().get(prop.getProperty("url"));

        Pages.initPages(Base.getDriver());

        // Extent Report setup
        ExtentTest test = ExtentReportManager.getInstance().createTest(scenario.getName());
        ExtentReportManager.setTest(test);
        test.info("Test Started: " + scenario.getName());

        System.out.println("Browser launched: " + Thread.currentThread().getId());
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