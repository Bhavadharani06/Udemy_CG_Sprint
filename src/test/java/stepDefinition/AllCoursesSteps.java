package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import utility.AllFunctionality;
import utility.Pages;

public class AllCoursesSteps {

    AllFunctionality util = new AllFunctionality();

    // BACKGROUND

    @Given("user navigates directly to All Courses page")
    public void user_navigates_directly_to_all_courses_page() {
        Pages.get().allCoursesPage.navigateToAllCourses();
    }

    // COURSE PRESENCE

    @Then("user should see at least one course in All Courses")
    public void user_should_see_at_least_one_course() {

        int count = Pages.get().allCoursesPage.getCourseCount();

        Assert.assertTrue(
                count > 0,
                "No courses found in All Courses page"
        );
    }

    @Then("user should see the no courses message")
    public void user_should_see_no_courses_message() {

        boolean displayed = Pages.get().allCoursesPage.isNoCourseMessageDisplayed();
//      assertFalse
        Assert.assertTrue(
                displayed,
                "'Start learning' message not displayed"
        );
    }

    @Then("user gets the first course title")
    public void user_gets_first_course_title() {

        String title = Pages.get().allCoursesPage.getFirstCourseTitle();

        Assert.assertFalse(
                title.isEmpty(),
                "First course title is empty"
        );

        System.out.println("First course title: " + title);
    }

    // CLICK FIRST COURSE & PLAY

    @When("user clicks on the first course")
    public void user_clicks_on_first_course() throws InterruptedException {
        Pages.get().allCoursesPage.clickFirstCourse();
        Thread.sleep(3000);
    }

    @And("user plays the video")
    public void user_plays_the_video() {
        Pages.get().allCoursesPage.playVideo();
    }

    // NAVIGATE BACK

    @Then("user navigates back to All Courses page")
    public void user_navigates_back_to_all_courses() throws InterruptedException {
        Pages.get().allCoursesPage.navigateBackToAllCourses();
    }

    // ASSERTION

    @And("user asserts course count is more than 0")
    public void user_asserts_course_count_more_than_zero() {

        int count = Pages.get().allCoursesPage.getCourseCount();

        Assert.assertTrue(
                count > 0,
                "Expected courses but found 0"
        );
    }

    // SINGLE COURSE CHECK (Scenario Outline support)

    @Then("user should see course {string} in All Courses")
    public void user_should_see_course_in_all_courses(String courseName) {

        boolean found = Pages.get().allCoursesPage.isCoursePresent(courseName);

        Assert.assertTrue(
                found,
                "Course NOT found — " + courseName
        );
    }

    // DATATABLE (FIXED)

    @Then("user verifies the following courses are present:")
    public void user_verifies_following_courses_present(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {

            String courseName = row.get("courseName");

            boolean found = Pages.get().allCoursesPage.isCoursePresent(courseName);

            Assert.assertTrue(
                    found,
                    "Course NOT found — " + courseName
            );

            System.out.println("Verified (DataTable): " + courseName);
        }
    }

    // EXCEL

    @Then("user verifies courses from excel sheet {string}")
    public void user_verifies_courses_from_excel(String sheetName) throws Exception {

        String excelPath = System.getProperty("user.dir")
                + "/src/test/resources/TestData/TestData.xlsx";

        util.loadExcelFile(excelPath, sheetName);

        // Fetch specific cells
        String course1 = util.getDataFromSingleCell(2, 1);
        String course2 = util.getDataFromSingleCell(3, 1);

        // Verify course 1
        boolean found1 = Pages.get().allCoursesPage.isCoursePresent(course1);
        Assert.assertTrue(
                found1,
                "Course from Excel NOT found — " + course1
        );
        System.out.println("Verified (Excel): " + course1);

        // Verify course 2
        boolean found2 = Pages.get().allCoursesPage.isCoursePresent(course2);
        Assert.assertTrue(
                found2,
                "Course from Excel NOT found — " + course2
        );
        System.out.println("Verified (Excel): " + course2);

        util.closeExcel();
    }
}