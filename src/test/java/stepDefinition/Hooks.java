package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utility.Base;
<<<<<<< HEAD
import utility.HandleCookies;
=======
import utility.ExtentReportManager;
>>>>>>> aba8dffd8701e7154fd8751d391edd917fd460b6
import utility.Pages;
import utility.ScreenshotUtil;

public class Hooks {

    public static Properties prop;

<<<<<<< HEAD
    // 🔹 Load config
=======
    // Load config file
>>>>>>> aba8dffd8701e7154fd8751d391edd917fd460b6
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
<<<<<<< HEAD
    public void setup(io.cucumber.java.Scenario scenario) {
        loadConfig();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
=======
    public void setup(Scenario scenario) {

        loadConfig();

        ChromeOptions options = new ChromeOptions();
>>>>>>> aba8dffd8701e7154fd8751d391edd917fd460b6
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

<<<<<<< HEAD
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
=======
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
>>>>>>> aba8dffd8701e7154fd8751d391edd917fd460b6
    }
}
