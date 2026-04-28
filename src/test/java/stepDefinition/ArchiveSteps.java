package stepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.And; 
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.Pages;

public class ArchiveSteps {

    // NAVIGATE TO ARCHIVED TAB
    // NOTE: @Given("User is on My Learning page") is defined in WishlistSteps.java
    // Cucumber shares it automatically — no need to redefine here

    @When("User navigates to Archived tab")
    public void user_navigates_to_archived_tab() {
        Pages.get().archivePage.clickArchivedTab();
    }

    // MAIN FLOW 

    @Then("User handles archive based on availability")
    public void user_handles_archive_based_on_availability() throws InterruptedException {
        Pages.get().archivePage.handleArchiveFlow();
    }

    // ASSERTION
    @And("User verifies archive state after action")
    public void user_verifies_archive_state() {

        boolean isEmpty = Pages.get().archivePage.isArchiveEmpty();

        if (isEmpty) {
            // After unarchive → archive should be empty
            Assert.assertTrue(
                    Pages.get().archivePage.isArchiveNowEmpty(),
                    "Archive is NOT empty after unarchiving"
            );
        } else {
            // After archive → archive should have courses
            Assert.assertTrue(
                    Pages.get().archivePage.hasArchivedCourses(),
                    "Archive is still empty after archiving"
            );
        }
    }
}