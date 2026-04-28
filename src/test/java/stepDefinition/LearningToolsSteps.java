package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import utility.Base;
import utility.Pages;

public class LearningToolsSteps {

    @Given("User is on My Learning Page and Learning Tools")
    public void user_is_on_my_learning_page_and_learning_tools() throws InterruptedException {
        Base.getDriver().get("https://www.udemy.com/home/my-courses/");
        Thread.sleep(2000);
    }

    @When("User navigates to Learning Tools") 
    public void user_navigates_to_learning_tools() throws InterruptedException {
        Pages.get().learningToolsPage.navigateToLearningTools();
        Pages.get().learningToolsPage.clickAddNewReminder();
    }

    @Then("User creates a learning reminder for courseName {string} freq {string} time {string} and date {string}")
    public void user_creates_a_learning_reminder(
            String courseName, String freq, String time, String date)
            throws InterruptedException {

        Pages.get().learningToolsPage.selectCourse(courseName);
        Pages.get().learningToolsPage.clickNext();

        Pages.get().learningToolsPage.selectFrequency(freq);
        Pages.get().learningToolsPage.enterTime(time);

        if (freq.equalsIgnoreCase("Weekly")) {
            Pages.get().learningToolsPage.selectDay("Mo");
            Pages.get().learningToolsPage.selectDay("We");
            Pages.get().learningToolsPage.selectDay("Fr");
        }

        if (freq.equalsIgnoreCase("Once") && date != null && !date.isEmpty()) {
            Pages.get().learningToolsPage.enterDate(date);
        }

        Pages.get().learningToolsPage.clickNext();
        Pages.get().learningToolsPage.clickDone();

        // ASSERT MODAL CLOSED
        Assert.assertTrue(
                Pages.get().learningToolsPage.isModalClosed(),
                "Reminder modal did not close"
        );
    }

    @Then("the reminder should be visible on the Learning Tools page for {string}")
    public void assertReminderVisible(String courseName) {

        boolean found = Pages.get().learningToolsPage.isReminderCreated(courseName);

        Assert.assertTrue(
                found,
                "Reminder NOT created for course: " + courseName
        );
    }
}