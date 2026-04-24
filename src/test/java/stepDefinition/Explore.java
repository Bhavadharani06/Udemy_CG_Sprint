package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonstepsPage;
import utility.AllFunctionality;
import utility.Base;
import utility.Pages;

public class Explore extends Base{
	
	//Commonsteps commonsteps=new Commonsteps();
	//CommonstepsPage common = new CommonstepsPage(Base.getDriver());
	String url;
	String expectedURL =url;
	String expectedTitle;
	private String capturedTitle;
	private String capturedURL;


	 @Given("User navigates to {string}")
	    public void user_navigates_to(String url) {
	        Base.getDriver().get(url);
	    }

	 @When("User clicks on Explore menu")
	    public void user_clicks_on_explore_menu() {
	    	// 1. Wait for Cloudflare via the common page logic
	    	Pages.get().explorepage.waitForCloudflareToDisappear();
	        // 2. Perform the click
	    	Pages.get().explorepage.clickExplore();
	    }

	    @When("User chooses {string}")
	    public void user_chooses(String category) throws InterruptedException {
	    	Pages.get().explorepage.selectCategory(category);
	    }

	    @When("User selects subcategory {string}")
	    public void user_selects_subcategory(String subCategory) throws InterruptedException {
	    	Pages.get().explorepage.selectSubCategory(subCategory);
	    }

    @Then("Courses should be displayed")
    public void verifyCourses() {
        Assert.assertTrue(Pages.get().explorepage.isCoursesDisplayed());
    }

    @When("User clicks on the first available course")
    public void clickFirstCourse() {
        String result = Pages.get().explorepage.clickFirstCourse();
        String[] data = result.split("\\|\\|");
        
        // Split by newline and take only the first line (the title)
        this.expectedTitle = data[1].split("\n")[0].trim(); 
        this.expectedURL = data[0].trim();
    }
    
    @Then("The course page URL should match the selected course URL")
    public void verifyURL() {
        // 1. Switch to the new tab first!
        Pages.get().explorepage.switchTab();

        // 2. Wait for the URL to change from the category page to the course page
        WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("courses/it-and-software")));
        } catch (Exception e) {
            System.out.println("Wait timed out or URL didn't change as expected.");
        }

        String actualUrl = Base.getDriver().getCurrentUrl();

        System.out.println("Expected URL (captured from list): " + expectedURL);
        System.out.println("Actual URL (on current page): " + actualUrl);

        Assert.assertNotNull(expectedURL, "Automation Bug: expectedURL was not captured in the previous step!");
        
        Assert.assertTrue(
            actualUrl.contains(expectedURL.split("\\?")[0]), 
            "URL mismatch! Expected page to contain: " + expectedURL
        );
    }
    
    


    @Then("Course list should be displayed")
    public void listDisplayed() {
        Assert.assertTrue(Pages.get().explorepage.isCoursesDisplayed());
    }

//    @When("User clicks on the first course")
//    public void clickCourse() {
//    	Pages.get().explorepage.clickFirstCourse();
//    }

    @Then("The opened course title should match the selected course")
    public void verifyTitle() {
        Pages.get().explorepage.switchTab();
        String actualTitle = Pages.get().explorepage.getCourseTitle().trim();

        System.out.println("Actual from page: [" + actualTitle + "]");
        System.out.println("Expected from list: [" + expectedTitle + "]");

        Assert.assertNotNull(expectedTitle, "Title not captured from course list");

        // WE FLIP IT HERE: Check if the big string (expectedTitle) 
        // contains the specific title on the page (actualTitle)
        Assert.assertTrue(
            expectedTitle.toLowerCase().contains(actualTitle.toLowerCase()),
            "Title mismatch! The captured text from the list did not contain the page title."
        );
    }

    // ================= FILTER =================

    @Then("Filtered courses should be displayed")
    public void filters() {
        Assert.assertTrue(Pages.get().explorepage.isCoursesDisplayed());
    }


    @When("User clicks on Trending courses")
    public void trending() {
    	Pages.get().explorepage.clickTrending();
    }
//
//    @When("User selects the first visible course")
//    public void trendingCourse() {
//    	Pages.get().explorepage.clickFirstCourse();
//    }
    @When("User selects the first visible course")
    public void trendingCourse() {

        String result = Pages.get().explorepage.clickFirstCourse();

        String[] data = result.split("\\|\\|");

        this.expectedTitle = data[1].split("\n")[0].trim();
        this.expectedURL = data[0].trim();

        // ✅ ADD THIS LINE (CRITICAL)
        Pages.get().explorepage.switchTab();
    }
    
    @When("User selects sort option")
    public void sort() { Pages.get().explorepage.selectSortOption(); }

    @When("User filters courses with rating")
    public void rating() { Pages.get().explorepage.selectRating(); }

    @When("User selects video duration")
    public void duration() { Pages.get().explorepage.selectVideoDuration(); }

    @When("User selects topic")
    public void topic() { Pages.get().explorepage.selectTopic(); }

    @When("User selects subcategory filter")
    public void subcatFilter() { Pages.get().explorepage.selectSubCategoryFilter(); }

    @When("User selects level")
    public void level() { Pages.get().explorepage.selectLevel(); }

    @When("User selects language")
    public void lang() { Pages.get().explorepage.selectLanguage(); }

    @When("User selects price")
    public void price() { Pages.get().explorepage.selectPrice(); }

//    @Then("User should be navigated to the course detail page")
//    public void coursePage() {
//        Assert.assertTrue(Base.getDriver().getCurrentUrl().contains("course"));
//    }
    
    @Then("User should be navigated to the course detail page")
    public void coursePage() {

        WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("/course/"));

        String currentUrl = Base.getDriver().getCurrentUrl();

        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("/course/"), "Not navigated to course page!");
    }
//
//    @Then("Course title should match the selected course")
//    public void matchTitle() {
//        Assert.assertEquals(Pages.get().explorepage.getCourseTitle(), Pages.get().explorepage.expectedTitle);
//    }
//    @When("User hovers on first course and clicks Add to Cart")
//    public void addtoCart() {
//    	//js.executeScript("window.scrollTo({top:1000, behavior: 'smooth'});");
//        Pages.get().explorepage.clickFirstCourse();
//        Pages.get().explorepage.clickAddToCart();
//    }
    
    @When("Instructor adds course to cart")
    public void addtoCart() {

        // already clicked in previous step → now switch tab
    	Pages.get().explorepage.hoverOnFirstCourse();              // ✅ open course page
//      pages.page5.clickAddToCart();

        // click add to cart on course page
        Pages.get().explorepage.clickGoToCart();
    }
    
//    @Then("Course title should match the selected course")
//    public void matchTitle() {
//
//        String actualTitle = Pages.get().explorepage.getCourseTitle().trim();
//
//        System.out.println("Expected: " + expectedTitle);
//        System.out.println("Actual: " + actualTitle);
//
//        Assert.assertNotNull(expectedTitle, "expectedTitle is NULL - click step failed");
//
//        Assert.assertTrue(
//            actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
//            "Title mismatch!"
//        );
//    }
    
    @Then("Course title should match the selected course")
    public void matchTitle() {
        String actualTitle = Pages.get().explorepage.getCourseTitle().trim();
        
        System.out.println("Expected (Captured): " + expectedTitle);
        System.out.println("Actual (H1): " + actualTitle);

        Assert.assertNotNull(expectedTitle, "Automation Error: expectedTitle was never captured!");

        // Use contains + toLowerCase to handle text differences like 'New' or 'Bestseller' tags
        boolean isMatch = actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()) || 
                          expectedTitle.toLowerCase().contains(actualTitle.toLowerCase());

        Assert.assertTrue(isMatch, "Title mismatch! Expected '" + expectedTitle + "' to match '" + actualTitle + "'");
    }
    
    @When("User clicks on Subscribe\\/Trial button")
    public void subscribe() {
    	Pages.get().explorepage.clickSubscribe();
    }

    @Then("User should be redirected to payment page")
    public void user_should_be_redirected_to_payment_page() {

        WebDriver driver = Base.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Wait for URL to contain checkout
        wait.until(ExpectedConditions.urlContains("checkout"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Payment Page URL: " + currentUrl);

        // ✅ Assertion
        Assert.assertTrue(
            currentUrl.contains("checkout"),
            "User is NOT on payment page"
        );
    }
   
  
    // ================= CART =================

//    @When("User clicks on Explore and selects {string}")
//    public void exploreAndSelect(String cat) {
//    	Pages.get().explorepage.clickExplore();
//    	Pages.get().explorepage.selectCategory(cat);
//    }

    @When("User hovers on first course and clicks Add to Cart")
    public void addCart() {
    	Pages.get().explorepage.hoverOnFirstCourse();
    	Pages.get().explorepage.clickAddToCart();
    }

//    @When("User clicks on Go to Cart")
//    public void goCart() {
//    	//Pages.get().explorepage.hoverOnFirstCourse();
//    	Pages.get().explorepage.clickGoToCart();
//    }

    @When("User clicks on Proceed to Checkout")
    public void checkout() {
    	Pages.get().explorepage.clickProceedToCheckout();
    }
    
    @Then("User should be redirected to authentication page")
    public void authPage() {
        Assert.assertTrue(Pages.get().explorepage.isOnPaymentPage());
    }

    @Then("User enters email from Excel")
    public void enterEmail() throws Exception {

        AllFunctionality excel = new AllFunctionality();
        excel.loadExcelFile("src/test/resources/testdata/TestData.xlsx", "Explore");

        String email = excel.getDataFromSingleCell(0, 1);
        Pages.get().explorepage.enterEmail(email);
    }

}
