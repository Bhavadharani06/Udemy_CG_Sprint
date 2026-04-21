package stepDefinition;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.Base;
import utility.Pages;
public class Hooks {

    public static WebDriver driver;
//    public static ExtentReports extent = AllFunctionality.getExtentReports();
//    public static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        // CRITICAL: Keeps browser open after execution
        options.setExperimentalOption("detach", true);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        Base.driver = driver;

        Pages.initPages(driver);

//        test = extent.createTest(scenario.getName());
//        test.info("Browser launched for: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            test.fail("Scenario Failed");
//        } else {
//            test.pass("Scenario Passed");
//        }
    	System.out.println("browser closed");
        
        // driver.quit(); // Leave commented to keep browser open for your review
    }

    //@AfterAll
//    public static void flushReport() {
//        if (extent != null) {
//            extent.flush();
//        }
//    }
}