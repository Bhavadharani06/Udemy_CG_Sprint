package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utility.Base;
import utility.ExtentReportManager;
import utility.Pages;
import utility.ScreenshotUtil;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.MediaEntityBuilder;



public class Hooks {
	ExtentReports extent;
	ExtentTest test;


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
    public void setup(Scenario scenario) {
    	
        loadConfig();

        ChromeOptions options = new ChromeOptions();

        // ⚠️ REMOVE USER PROFILE FOR PARALLEL
        // options.addArguments("user-data-dir=...");
        // options.addArguments("profile-directory=Default");

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        // ✅ SET DRIVER IN THREADLOCAL
        Base.setDriver(driver);

        Base.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Base.getDriver().get(prop.getProperty("url"));

        // ✅ INIT PAGES PER THREAD
        Pages.initPages(Base.getDriver());
        // 4. Create Extent test node
        ExtentTest test = ExtentReportManager.getInstance()
                .createTest(scenario.getName());
        ExtentReportManager.setTest(test);
        ExtentReportManager.getTest().info("Test Started: " + scenario.getName());

        System.out.println("Browser launched: " + Thread.currentThread().getId());
    }

    @After
    public void tearDown(Scenario scenario) {
    	ExtentTest test = ExtentReportManager.getTest();

        if (scenario.isFailed()) {
            // ── Take screenshot and attach to Extent report ──
            String base64Screenshot = ScreenshotUtil.takeScreenshotAsBase64(Base.getDriver());
            test.fail("Scenario FAILED: " + scenario.getName(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

            // ── Also embed in Cucumber HTML report ──
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