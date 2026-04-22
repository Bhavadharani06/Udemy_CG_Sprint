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
//public class Explore1 extends Base {
//	
//	Commonsteps commonsteps=new Commonsteps();
//	CommonstepsPage common = new CommonstepsPage(driver);
//	String expectedURL;
//
//
//
//    @Then("Courses should be displayed")
//    public void courses_should_be_displayed() {
//        if (!Pages.page1.isCoursesDisplayed()) {
//            throw new RuntimeException(" Courses are NOT displayed");
//        } else {
//            System.out.println(" Courses are displayed");
//        }
//    }
//
//
//    @Then("The course page URL should match the selected course URL")
//    public void the_course_page_url_should_match_the_selected_course_url() {
//
//        String parentWindow = driver.getWindowHandle();
//
//        for (String window : driver.getWindowHandles()) {
//            if (!window.equals(parentWindow)) {
//                driver.switchTo().window(window);
//                break;
//            }
//        }
//
//        String actualURL = driver.getCurrentUrl();
//
//        String expectedPath = commonsteps.expectedURL.split("\\?")[0];
//        String actualPath = actualURL.split("\\?")[0];
//
//        if (actualPath.equals(expectedPath)) {
//            System.out.println("PASS");
//            System.out.println("Expected: " + expectedPath);
//            System.out.println("Actual: " + actualPath);
//        } else {
//            System.out.println("FAIL");
//            System.out.println("Expected: " + expectedPath);
//            System.out.println("Actual: " + actualPath);
//        }
//    }
//
//}
