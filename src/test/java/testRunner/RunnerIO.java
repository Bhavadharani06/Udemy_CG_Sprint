package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/java/featureFiles/add_all_no_duplicates.feature"},
    glue = "stepDefinition",
    dryRun = false
)
public class RunnerIO extends AbstractTestNGCucumberTests {

}