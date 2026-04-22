package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utility.TestNGListener;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
@CucumberOptions(


    features = "src/test/java/features",

    glue = "stepDefinition",

    tags = "@invalidurl",   

    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "junit:target/cucumber.xml"
    },

    monochrome = true,
    dryRun = false
)
public class RunnerIO extends AbstractTestNGCucumberTests {

    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}