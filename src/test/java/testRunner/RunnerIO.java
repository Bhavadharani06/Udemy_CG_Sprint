package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utility.TestNGListener;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
@CucumberOptions(
<<<<<<< HEAD

    features = "./src/test/java/features",

    glue = "stepDefinition",

    tags = "@invalidurl",   
=======
		features="src/test/java/features", 
		

    glue = "stepDefinition",

    tags = "@Instructor_parallel",   
>>>>>>> 0b7c4a2 (course-management:Updated Stepdefinition)

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
