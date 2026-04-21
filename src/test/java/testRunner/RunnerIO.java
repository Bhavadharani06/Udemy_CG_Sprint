//package testRunner;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//    features = "src/test/java/features/add_all_no_duplicates.feature",
//    glue = "stepDefinition",
//    
//    dryRun = false   
//)
//public class RunnerIO extends AbstractTestNGCucumberTests {
//
//}


//package testRunner;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//    features = "src/test/java/features",   // 👉 run all your features
//    glue = "stepDefinition",
//
//    plugin = {
//        "pretty",
//        "html:target/cucumber-report.html",
//        "json:target/cucumber.json",
//        "junit:target/cucumber.xml"
//    },
//
//    monochrome = true,
//    dryRun = false
//)
//public class RunnerIO extends AbstractTestNGCucumberTests {
//
//}


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

    tags = "@parallel",   

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