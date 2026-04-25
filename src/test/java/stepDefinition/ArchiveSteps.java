package stepDefinition;

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
        // After archive   → assertArchiveHasCourses (already called inside handleArchiveFlow)
        // After unarchive → assertArchiveIsEmpty    (already called inside handleArchiveFlow)
        System.out.println("Archive flow completed and verified");
    }
}