//package stepDefinition;
//
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//import io.cucumber.java.en.*;
//import pages.*;
//import utility.AllFunctionality;
//import utility.Pages;
//
//public class SearchManagement_Filter {
//
//	WebDriver driver = Hooks.driver;
//
//	private HomePage home;
//	private SearchResultsPage results;
//	private CartPage cart;
//
//	@Given("user is on Udemy homepage")
//	public void user_is_on_homepage() {
//
//		driver = Hooks.driver;
//
//		if (driver == null) {
//			throw new RuntimeException("Driver is NULL from Hooks ❌");
//		}
//
//		// ✅ USE Pages
//		home = Pages.homePage;
//		Assert.assertTrue(driver.getCurrentUrl().contains("udemy.com"), " Not on Udemy Homepage!");
//	}
//
//	
//		
//		@When("user searches for course at row {int} from sheet {string}")
//		public void user_searches_from_excel(int rowNum, String sheetName) throws Exception {
//			Thread.sleep(5000);
//
//		    AllFunctionality util = new AllFunctionality();
//		    String filePath = "./src/test/resources/testdata/TestData.xlsx";
//		    
//		    // Load and Fetch
//		    util.loadExcelFile(filePath, sheetName);
//		    String courseName = util.getDataFromSingleCell(rowNum, 1);
//		    util.closeExcel();
//		    
//		    System.out.println("Excel Data Found: " + courseName);
//		    Assert.assertNotNull(courseName, "Course name fetched from Excel is NULL!");
//		    // Execute Search
//		    home.searchCourse(courseName);
//		    results = Pages.searchResultsPage;
//		    Assert.assertTrue(driver.getCurrentUrl().contains("q="), "Search was not triggered!");
//		}
//	
//
//	@And("user applies certification, rating and language filters")
//	public void apply_multiple_filters() throws InterruptedException {
//		results.applyMainFilters();
//	}
//
//	@And("user applies free course filter")
//	public void apply_free_filter() throws InterruptedException {
//		results.applyFreeFilter();
//		Assert.assertTrue(driver.getCurrentUrl().contains("price=free"), "Free filter was not applied to URL!");
//	}
//
//	@And("user clears all filters")
//	public void clear_filters() throws InterruptedException {
//		results.clearAllFilters();
//		Thread.sleep(2000);
//		Assert.assertFalse(driver.getCurrentUrl().contains("price=free"), "❌ Filters were not cleared successfully!");
//	}
//
//	@And("user clicks on Add to Cart")
//	public void add_to_cart() throws InterruptedException {
//		Thread.sleep(2000);
//
//		results.clickAddToCart();
//
//		// ✅ USE Pages
//		cart = Pages.cartPage;
//		Assert.assertTrue(results.isAddToCartVisible(), "❌ Add to Cart button is not visible for this course!");
//	}
//
//	@Then("enroll now button should be visible")
//	public void verify_enroll_now_visible() {
//		Assert.assertTrue(results.isEnrollNowVisible(), "❌ Enroll Now button is NOT visible");
//		System.out.println("✅ Enroll Now button is visible");
//	}
//
//	@Then("course should be added to cart")
//	public void verify_cart() {
//		Assert.assertTrue(cart.isCourseAdded(), "❌ Course NOT added to cart");
//		System.out.println("✅ Course added successfully");
//	}
//   
//	@Then("no courses should be displayed")
//	public void invalid_search() {
//		Assert.assertTrue(results.isNoResultsDisplayed(), "❌ No-results message NOT displayed");
//		System.out.println("✅ No results displayed correctly");
//	}
//	@Then("results should be displayed for the searched course")
//	public void verify_results_displayed() {
//	    // Replace with the actual method in your SearchResultsPage that checks if results exist
//	    Assert.assertTrue(results.isResultsLoaded(), "❌ Search results were not displayed!");
//	    System.out.println("✅ Search results verified successfully");
//	}
//}