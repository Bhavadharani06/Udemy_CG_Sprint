package stepDefinition;

import io.cucumber.java.en.*;
import utility.Base;
import utility.Pages;

public class ArchiveSteps {

	@Given("User Should be in My Learning page")
	public void user_should_be_in_my_learning_page() {
		Base.driver.get("https://www.udemy.com/home/my-courses/");
		System.out.println("Navigated to My Learning page via cookies");
	}

	@When("User navigates to Archived tab")
	public void user_navigates_to_archived_tab() {
		Pages.archivePage.clickArchivedTab();
	}

	@Then("User handles archive based on availability")
	public void user_handles_archive_based_on_availability() {
		Pages.archivePage.handleArchiveFlow();
	}
}