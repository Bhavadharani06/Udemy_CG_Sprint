package stepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonstepsPage;
import utility.AllFunctionality;
import utility.Base;
import utility.Pages;

public class Explore extends Base{
	
	Commonsteps commonsteps=new Commonsteps();
	CommonstepsPage common = new CommonstepsPage(driver);
	String expectedURL;

    // ================= COMMON =================

    @Given("User navigates to {string}")
    public void open(String url) {
        Pages.explorepage.openUrl(url);
    }

    @When("User clicks on Explore menu")
    public void clickExplore() {
    	Pages.explorepage.waitForCloudflare();
    	Pages.explorepage.clickExplore();
    }

    @When("User chooses {string}")
    public void chooseCategory(String cat) {
    	Pages.explorepage.selectCategory(cat);
    }

    @When("User selects subcategory {string}")
    public void selectSub(String sub) {
    	Pages.explorepage.selectSubCategory(sub);
    }

    // ================= NAVIGATION =================

    @Then("Courses should be displayed")
    public void verifyCourses() {
        Assert.assertTrue(Pages.explorepage.isCoursesDisplayed());
    }

    @When("User clicks on the first available course")
    public void clickFirstCourse() {
    	Pages.explorepage.clickFirstCourse();
    }

    @Then("The course page URL should match the selected course URL")
    public void verifyURL() {
    	Pages.explorepage.switchTab();

        String actual = driver.getCurrentUrl().split("\\?")[0];
        String expected = Pages.explorepage.expectedURL.split("\\?")[0];

        Assert.assertEquals(actual, expected);
    }

    // ================= TITLE =================

    @Then("Course list should be displayed")
    public void listDisplayed() {
        Assert.assertTrue(Pages.explorepage.isCoursesDisplayed());
    }

    @When("User clicks on the first course")
    public void clickCourse() {
    	Pages.explorepage.clickFirstCourse();
    }

    @Then("The opened course title should match the selected course")
    public void verifyTitle() {
    	Pages.explorepage.switchTab();
        Assert.assertEquals(Pages.explorepage.getCourseTitle(), Pages.explorepage.expectedTitle);
    }

    // ================= FILTER =================

    @Then("Filtered courses should be displayed")
    public void filters() {
        Assert.assertTrue(Pages.explorepage.isCoursesDisplayed());
    }

    // ================= TRENDING =================

    @When("User clicks on Trending courses")
    public void trending() {
    	Pages.explorepage.clickTrending();
    }

    @When("User selects the first visible course")
    public void trendingCourse() {
    	Pages.explorepage.clickFirstCourse();
    }

    @Then("User should be navigated to the course detail page")
    public void coursePage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("course"));
    }

    @Then("Course title should match the selected course")
    public void matchTitle() {
        Assert.assertEquals(Pages.explorepage.getCourseTitle(), Pages.explorepage.expectedTitle);
    }

    @When("User clicks on Subscribe\\/Trial button")
    public void subscribe() {
    	Pages.explorepage.clickSubscribe();
    }

    @Then("User should be redirected to authentication page")
    public void authPage() {
        Assert.assertTrue(Pages.explorepage.isOnAuthPage());
    }

    // ================= CART =================

    @When("User clicks on Explore and selects {string}")
    public void exploreAndSelect(String cat) {
    	Pages.explorepage.clickExplore();
    	Pages.explorepage.selectCategory(cat);
    }

    @When("User hovers on first course and clicks Add to Cart")
    public void addCart() {
    	Pages.explorepage.hoverFirstCourse();
    	Pages.explorepage.clickAddToCart();
    }

    @When("User clicks on Go to Cart")
    public void goCart() {
    	Pages.explorepage.clickGoToCart();
    }

    @When("User clicks on Proceed to Checkout")
    public void checkout() {
    	Pages.explorepage.clickCheckout();
    }

    @Then("User enters email from Excel")
    public void enterEmail() throws Exception {

        AllFunctionality excel = new AllFunctionality();
        excel.loadExcelFile("src/test/resources/testdata/TestData.xlsx", "Explore");

        String email = excel.getDataFromSingleCell(0, 1);
        Pages.explorepage.enterEmail(email);
    }

}
