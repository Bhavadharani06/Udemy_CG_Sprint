package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import utility.Base;
import utility.Pages;

public class MyListSteps {

	@Given("User is on My Learning page and My List")
	public void user_is_on_my_learning_page_and_my_list() {
//	    Base.driver.get("https://www.udemy.com/home/my-courses/");

		Pages.get().signUpPage.clickSignUp();

		Pages.get().signUpPage.waitForCaptcha();
		Pages.get().signUpPage.enterName("Bhavadharani V");
		Pages.get().signUpPage.enterEmail("bhavadharanivs06@gmail.com");
		Pages.get().signUpPage.clickContinue();
		Pages.get().signUpPage.waitForOTP();
		Pages.get().signUpPage.clickFinalSignUp();
		System.out.println("SignUp Successfully");
		System.out.println("Clicking on My Learning...");

	}

	@When("User navigates to My Lists tab")
	public void user_navigates_to_my_lists_tab() {
		Pages.get().myListPage.clickMyListTab();
	}

	@Then("User handles My List flow with listName {string} and description {string}")
	public void user_handles_my_list_flow(String listName, String desc) {
		Pages.get().myListPage.handleMyListFlow(listName, desc);
	}

	@Then("User verifies list is already present")
	public void user_verifies_list_is_already_present() {

		boolean isEmpty = Pages.get().myListPage.isListEmpty();

		if (!isEmpty) {
			System.out.println("✅ List already exists");
		} else {
			throw new AssertionError("❌ No list found");
		}
	}
}