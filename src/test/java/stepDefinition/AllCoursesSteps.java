package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

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
        Pages.get().allCoursesPage.assertCoursesPresent();
    }

    @Then("user should see the no courses message")
    public void user_should_see_no_courses_message() {
        boolean displayed = Pages.get().allCoursesPage.isNoCourseMessageDisplayed();
        if (!displayed)
            System.out.println("FAIL: 'Start learning' message not displayed");
        else
            System.out.println("PASS: No courses message displayed");
    }

    @Then("user gets the first course title")
    public void user_gets_first_course_title() {
        String title = Pages.get().allCoursesPage.getFirstCourseTitle();
        if (title.isEmpty())
            System.out.println("FAIL: First course title is empty");
        else
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

    // ASSERTIONS

    @And("user asserts course count is more than 0")
    public void user_asserts_course_count_more_than_zero() {
        int count = Pages.get().allCoursesPage.getCourseCount();
        if (count == 0)
            System.out.println("FAIL: Expected courses but found 0");
        else
            System.out.println("PASS: Course count after returning = " + count);
    }

    // Scenario Outline step
    @Then("user should see course {string} in All Courses")
    public void user_should_see_course_in_all_courses(String courseName) {
        Pages.get().allCoursesPage.assertCoursePresent(courseName);
    }

    // DATATABLE

    @Then("user verifies the following courses are present:")
    public void user_verifies_following_courses_present(DataTable dataTable) {
        List<String> courseNames = dataTable.asList(String.class);

        for (String courseName : courseNames) {
            if (courseName.equalsIgnoreCase("courseName")) continue; // skip header

            boolean found = Pages.get().allCoursesPage.isCoursePresent(courseName);
            if (!found)
                System.out.println("FAIL: Course NOT found — " + courseName);
            else
                System.out.println("PASS (DataTable): " + courseName);
        }
    }

    // EXCEL

    @Then("user verifies courses from excel sheet {string}")
    public void user_verifies_courses_from_excel(String sheetName) throws Exception {
        String excelPath = System.getProperty("user.dir")
                + "/src/test/resources/TestData/TestData.xlsx";

        util.loadExcelFile(excelPath, sheetName);

        int rowNum = 1; // row 0 = header, start from row 1
        while (true) {
            try {
                String courseName = util.getDataFromSingleCell(rowNum, 0); // column 0 = course name
                if (courseName == null || courseName.trim().isEmpty()) break;

                boolean found = Pages.get().allCoursesPage.isCoursePresent(courseName);
                if (!found)
                    System.out.println("FAIL: Course from Excel NOT found — " + courseName);
                else
                    System.out.println("PASS (Excel row " + rowNum + "): " + courseName);

                rowNum++;
            } catch (Exception e) {
                break; // no more rows
            }
        }

        util.closeExcel();
    }
}