package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.Base;
import utility.Pages;

public class AllCoursesSteps {

	@Given("user is on My Learning page")
	public void user_is_on_my_learning_page() {
//	    Base.driver.get("https://www.udemy.com/home/my-courses/");
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

	@Then("user should see course {string} in All Courses page")
	public void user_should_see_course_in_all_courses_page(String string) {

		Pages.allCoursesPage.clickAllCourses(Base.driver);

		Pages.allCoursesPage.verifyCoursePresent(Base.driver, string);
	}

	@Then("user should not see course {string} in All Courses page")
	public void user_should_not_see_course_in_all_courses_page(String string) {

		Pages.allCoursesPage.clickAllCourses(Base.driver);

		boolean result = Pages.allCoursesPage.isCoursePresent(Base.driver, string);

		if (result) {
			throw new AssertionError("Course SHOULD NOT be present: " + string);
		} else {
			System.out.println("Course correctly NOT present: " + string);
		}
	}

	@Then("user should see {string} message in All Courses page")
	public void user_should_see_message_in_all_courses_page(String string) {

		Pages.allCoursesPage.clickAllCourses(Base.driver);

		boolean result = Pages.allCoursesPage.isNoCourseMessageDisplayed(Base.driver);

		if (result) {
			System.out.println("Message displayed: " + string);
		} else {
			throw new AssertionError("Message NOT displayed: " + string);
		}
	}
}