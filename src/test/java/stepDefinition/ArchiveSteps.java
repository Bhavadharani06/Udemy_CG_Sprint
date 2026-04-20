package stepDefinition;

import io.cucumber.java.en.*; 
import utility.Pages;


public class ArchiveSteps {

	@Given("User Should be in My Learning page")
	public void user_should_be_in_my_learning_page() {
//		try {
//			Thread.sleep(10000);
//		} catch (Exception e) {
//		}
//		Base.driver.get("https://www.udemy.com/home/my-courses/");
		
		Pages.signUpPage.clickSignUp();
		
		Pages.signUpPage.waitForCaptcha();
        Pages.signUpPage.enterName("Bhavadharani V");
        Pages.signUpPage.enterEmail("bhavadharanivs06@gmail.com");
        Pages.signUpPage.clickContinue();
        Pages.signUpPage.waitForOTP();
        Pages.signUpPage.clickFinalSignUp();
        System.out.println("SignUp Successfully");
        Pages.homePage.getMyLearning().click();
        System.out.println("Clicking on My Learning...");

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