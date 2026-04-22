//package stepDefinition;
//
//import org.openqa.selenium.WebDriver;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.Explore4Page;
//import utility.Base;
//import utility.Pages;
//
//public class Explore4 extends Base{
//
//
//    @When("User clicks on Trending courses")
//    public void user_clicks_trending() {
//    	Pages.page4.clickTrending();
//    }
//
//    @When("User selects the first visible course")
//    public void user_selects_first_course() {
//    	Pages.page4.selectFirstCourse();
//    }
//
//    @Then("User should be navigated to the course detail page")
//    public void user_on_course_page() {
//        // Optional validation can be added
//    }
//
//    @Then("Course title should match the selected course")
//    public void validate_course_title() {
//        if (!Pages.page4.validateCourseTitle()) {
//            throw new AssertionError("Title mismatch!");
//        }
//    }
//    @When("User clicks on Subscribe\\/Trial button")
//    public void user_clicks_subscribe() {
//        Pages.page4.clickSubscribe();
//    }
//    
//    @Then("User should be redirected to authentication page")
//    public void user_redirected_to_auth() {
//        if (!Pages.page4.isOnAuthPage()) {
//            throw new AssertionError("Not navigated to Auth page!");
//        }
//        driver.quit();
//    }
//	
//}
//
