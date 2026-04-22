package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src\\test\\java\\features\\Explore.feature"},
    glue = "stepDefinition",
    dryRun = false,tags="@navigation and @url",
    monochrome = true
)
public class RunnerIO extends AbstractTestNGCucumberTests {

}