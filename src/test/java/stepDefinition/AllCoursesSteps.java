package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utility.AllFunctionality;
import utility.Base;
import utility.Pages;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

public class AllCoursesSteps {

    // BACKGROUND
    @Given("user is logged in and navigated to All Courses page")
    public void user_is_logged_in_and_navigated_to_all_courses_page() {
        // Assumes login already completed in a Login step / hook
        Pages.allCoursesPage.navigateToAllCourses();
        System.out.println("Navigated directly to All Courses page");
    }
    
    // GUARD — at least one course must be enrolled
 
    @Given("user has at least one course enrolled")
    public void user_has_at_least_one_course_enrolled() {
        if (Pages.allCoursesPage.isNoCourseMessageDisplayed()) {
            System.out.println("No courses enrolled — stopping test. "
                + "Please enroll in a course manually and re-run.");
            throw new AssertionError(
                "SKIP: No courses enrolled. Nothing to play — "
                + "enroll in at least one course first.");
        }
        System.out.println("Course(s) found — proceeding.");
    }

    // COURSE PRESENCE CHECKS

    @Then("user should see Start learning message")
    public void user_should_see_start_learning_message() {
        Pages.allCoursesPage.assertNoCoursesMessage();
    }

    @Then("user should see course {string} in All Courses page")
    public void user_should_see_course_in_all_courses_page(String courseName) {
        Pages.allCoursesPage.assertCoursePresent(courseName);
    }

    @Then("user should NOT see course {string} in All Courses page")
    public void user_should_not_see_course_in_all_courses_page(String courseName) {
        Pages.allCoursesPage.assertCourseNotPresent(courseName);
    }

    @Then("user should see courses on the All Courses page")
    public void user_should_see_courses_on_the_all_courses_page() {
        Pages.allCoursesPage.assertCoursesPresent();
    }

    @Then("user should see exactly {int} courses in All Courses page")
    public void user_should_see_exactly_n_courses(int count) {
        Pages.allCoursesPage.assertCourseCount(count);
    }

    // COURSE CLICK

    @When("user clicks on the first course")
    public void user_clicks_on_the_first_course() throws InterruptedException {
        Pages.allCoursesPage.clickFirstCourse();
    }

    // VIDEO PLAYER ACTIONS

    @When("user plays the video")
    public void user_plays_the_video() throws InterruptedException {
        Pages.allCoursesPage.playVideo();
    }

    @When("user toggles the volume")
    public void user_toggles_the_volume() throws InterruptedException {
        Pages.allCoursesPage.toggleVolume();
    }

 
    @When("user changes playback settings with the following options:")
    public void user_changes_playback_settings(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String type  = row.get("settingType");
            String value = row.get("value");

            switch (type.toLowerCase()) {
                case "speed":
                    int speedIndex = Integer.parseInt(value.trim());
                    Pages.allCoursesPage.changeSpeed(speedIndex);
                    System.out.println("Applied speed index: " + speedIndex
                        + " = " + Pages.allCoursesPage.getSpeedLabel(speedIndex));
                    break;
                case "quality":
                    Pages.allCoursesPage.changeQuality(value);
                    System.out.println("Applied quality: " + value);
                    break;
                default:
                    System.out.println("Unknown setting type: " + type);
            }
        }
    }


    @Then("user should be able to select each speed from the list:")
    public void user_should_be_able_to_select_each_speed(DataTable dataTable)
            throws InterruptedException {
        List<String> rows = dataTable.asList();
        rows.remove(0); // remove header "speedIndex"

        for (String indexStr : rows) {
            int speedIndex = Integer.parseInt(indexStr.trim());
            Pages.allCoursesPage.changeSpeed(speedIndex);
            System.out.println("Speed index " + speedIndex
                + " = " + Pages.allCoursesPage.getSpeedLabel(speedIndex));
            Thread.sleep(500);
        }
    }

    // ASSERTION — course name inside player

    @Then("user should see the course title in the player")
    public void user_should_see_the_course_title_in_the_player() {
        String title = Pages.allCoursesPage.getCourseNameFromPlayer();
        if (title == null || title.isEmpty()) {
            throw new AssertionError("FAIL: Course title not visible in player");
        }
        System.out.println("PASS: Course title in player = " + title);
    }

    @Then("user should see course title {string} in the player")
    public void user_should_see_course_title_in_player(String expectedTitle) {
        Pages.allCoursesPage.assertCourseNameInPlayer(expectedTitle);
    }

    // NAVIGATION BACK

    @When("user navigates back to All Courses page")
    public void user_navigates_back_to_all_courses_page() throws InterruptedException {
        Pages.allCoursesPage.navigateBackToAllCourses();
    }

 
    // EXCEL-DRIVEN STEP

    @Then("user verifies courses from Excel file {string} on sheet {string}")
    public void user_verifies_courses_from_excel(String filePath, String sheetName)
            throws Exception {

        String fullPath = System.getProperty("user.dir") + "/src/test/resources/" + filePath;

        Object[][] data = new AllFunctionality().getExcelDataAsArray(fullPath, sheetName);

        System.out.println("Reading Excel using utility: " + filePath + " | Sheet: " + sheetName);

        for (Object[] row : data) {

            String courseName = row[0].toString().trim();
            String shouldExist = row[1].toString().trim().toUpperCase();

            System.out.println("Course: " + courseName + " | ShouldExist: " + shouldExist);

            if ("TRUE".equals(shouldExist)) {
                Pages.allCoursesPage.assertCoursePresent(courseName);
            } else {
                Pages.allCoursesPage.assertCourseNotPresent(courseName);
            }
        }

        System.out.println("Excel verification complete using utility");
    }
}
