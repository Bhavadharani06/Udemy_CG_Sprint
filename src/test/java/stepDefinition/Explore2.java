//package stepDefinition;
//import org.testng.Assert;
//
//import stepDefinition.*;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.CommonstepsPage;
//import utility.Base;
//import utility.Pages;
//
//public class Explore2 extends Base{
//
//	
//	String expectedTitle;
//
//    @Then("Course list should be displayed")
//    public void course_list_should_be_displayed() {
//
//        if (!Pages.page2.isCoursesDisplayed()) {
//            throw new RuntimeException("Courses are NOT displayed");
//        } else {
//            System.out.println("Courses are displayed");
//        }
//    }
//
//    @When("User clicks on the first course")
//    public void user_clicks_on_the_first_course() {
//
//    	expectedTitle = Pages.page2.getFirstCourseTitle();
//
//    	Pages.page2.clickFirstCourse();
//    }
//
//    @Then("The opened course title should match the selected course")
//    public void the_opened_course_title_should_match() {
//
//        // switch tab
//    	Pages.page2.switchToNewTabIfPresent();
//
//        //  get actual title
//        String actualTitle = Pages.page2.getCourseTitle();
//
//        System.out.println("Expected Title: " + expectedTitle);
//        System.out.println("Actual Title: " + actualTitle);
//
//        if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
//            throw new RuntimeException(
//                "Wrong course opened Expected: " + expectedTitle +
//                " Actual: " + actualTitle
//            );
//        }
//
//        System.out.println("PASS: Correct course opened");
//    }
//
//}
