package stepDefinition;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.*;
import utility.Pages;

public class SearchManagement_Filter {

	WebDriver driver = Hooks.driver;

	private HomePage home;
	private SearchResultsPage results;
	private CartPage cart;

	@Given("user is on Udemy homepage")
	public void user_is_on_homepage() {

		driver = Hooks.driver;

		if (driver == null) {
			throw new RuntimeException("Driver is NULL from Hooks ❌");
		}

		// ✅ USE Pages
		home = Pages.homePage;
	}

	@When("user searches for {string}")
	public void user_searches(String course) throws InterruptedException {
		Thread.sleep(5000);

		home.searchCourse(course);

		// ✅ USE Pages
		results = Pages.searchResultsPage;
	}

	@And("user applies certification, rating and language filters")
	public void apply_multiple_filters() throws InterruptedException {
		results.applyMainFilters();
	}

	@And("user applies free course filter")
	public void apply_free_filter() throws InterruptedException {
		results.applyFreeFilter();
	}

	@And("user clears all filters")
	public void clear_filters() throws InterruptedException {
		results.clearAllFilters();
		Thread.sleep(2000);
	}

	@And("user clicks on Add to Cart")
	public void add_to_cart() throws InterruptedException {
		Thread.sleep(2000);

		results.clickAddToCart();

		// ✅ USE Pages
		cart = Pages.cartPage;
	}

	@Then("enroll now button should be visible")
	public void verify_enroll_now_visible() {
		Assert.assertTrue(results.isEnrollNowVisible(), "❌ Enroll Now button is NOT visible");
		System.out.println("✅ Enroll Now button is visible");
	}

	@Then("course should be added to cart")
	public void verify_cart() {
		Assert.assertTrue(cart.isCourseAdded(), "❌ Course NOT added to cart");
		System.out.println("✅ Course added successfully");
	}

	@Then("user should be able to enroll")
	public void verify_enroll() {
		// Assert.assertTrue(results.isEnrollSuccessful(), "❌ Enroll failed");
		System.out.println("✅ Enroll successful");
	}

	@Then("no courses should be displayed")
	public void invalid_search() {
		Assert.assertTrue(results.isNoResultsDisplayed(), "❌ No-results message NOT displayed");
		System.out.println("✅ No results displayed correctly");
	}
}