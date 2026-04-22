package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.Pages;

public class LearningToolsSteps {
	@Given("User is on My Learning Page and Learning Tools")
	public void user_is_on_my_learning_page_and_learning_tools() {
//		Base.driver.get("https://www.udemy.com/home/my-courses/");

		Pages.signUpPage.clickSignUp();

		Pages.signUpPage.waitForCaptcha();
		Pages.signUpPage.enterName("Bhavadharani V");
		Pages.signUpPage.enterEmail("bhavadharani2608@gmail.com");
		Pages.signUpPage.clickContinue();
		Pages.signUpPage.waitForOTP();
		Pages.signUpPage.clickFinalSignUp();
		System.out.println("SignUp Successfully");
		System.out.println("Clicking on My Learning...");
	}

	@When("User navigates to Learning Tools")
	public void user_navigates_to_learning_tools() {
		Pages.learningToolsPage.clickLearningToolsTab();
	}

	@Then("User creates a learning reminder for courseName {string} freq {string} time {string} and date {string}")
	public void user_creates_a_learning_reminder_for_course_name_freq_time_and_date(String courseName, String freq,
			String time, String date) {
		Pages.learningToolsPage.createReminderFlow(courseName, time, date, freq);
	}

}
