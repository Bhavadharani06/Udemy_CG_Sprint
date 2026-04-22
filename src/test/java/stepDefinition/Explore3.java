//package stepDefinition;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.CommonstepsPage;
//import pages.Explore3Page;
//import utility.Base;
//import utility.Pages;
//
//public class Explore3 extends Base {
//
//    // FILTER STEPS
//
//
//    @When("User chooses {string} category")
//    public void user_chooses_category(String category) {
//        // Access through centralized Pages utility
//        Pages.page3.selectCategory(category);
//        Pages.page3.scrollToFilters();
//    }
//    
//    @When("User selects sort option")
//    public void user_selects_sort_option() {
//        Pages.page3.selectSortOption();
//    }
//
//    @When("User filters courses with rating")
//    public void user_filters_courses_with_rating() {
//        Pages.page3.selectRating();
//    }
//
//    @When("User selects video duration")
//    public void user_selects_video_duration() {
//        Pages.page3.selectVideoDuration();
//    }
//
//    @When("User selects topic")
//    public void user_selects_topic() {
//        Pages.page3.selectTopic();
//    }
//
//    @When("User selects subcategory filter")
//    public void user_selects_subcategory_filter() {
//        Pages.page3.selectSubCategoryFilter();
//    }
//
//    @When("User selects level")
//    public void user_selects_level() {
//        Pages.page3.selectLevel();
//    }
//
//    @When("User selects language")
//    public void user_selects_language() {
//        Pages.page3.selectLanguage();
//    }
//
//    @When("User selects price")
//    public void user_selects_price() {
//        Pages.page3.selectPrice();
//    }
//
//    @Then("Filtered courses should be displayed")
//    public void filtered_courses_should_be_displayed() {
//        // Using Assert for proper TestNG reporting
//        boolean isDisplayed = Pages.page3.verifyCoursesDisplayed();
//        Assert.assertTrue(isDisplayed, "Error: No courses found after applying filters.");
//        System.out.println("Filters applied successfully and courses are visible.");
//    }
//    
//    
//    
////    @When("User clicks on the first available course")
////    public void user_clicks_on_the_first_available_course() {
////        Commonsteps.expectedURL = Pages.page3.clickRandomCourse();  // ✅ STORE URL
////    }
//
//}
