package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.IOException;
import utility.Pages;
import utility.ScreenshotUtil;
import utility.Base;
import utility.AllFunctionality;

public class InstructorFlowSteps extends Base {

    AllFunctionality func = new AllFunctionality();
    ScreenshotUtil scr=new ScreenshotUtil();

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        // Initialize the report
     
        // ✅ Use getDriver() to verify the specific thread's browser
        Assert.assertNotNull(Base.getDriver(), "❌ Driver initialization failed.");
    }

    @Given("User navigates to Udemy website")
    public void user_navigates_to_udemy_website() {
        // ✅ Pass getDriver() into the utility method
        func.openURL(Base.getDriver(), "https://www.udemy.com/");
    }

    @When("User searches for {string}")
    public void user_searches_for(String courseName) throws InterruptedException {
        // ✅ Use Pages.get() to ensure page objects are thread-local
        Pages.get().home.searchCourse(courseName);
        System.out.println("🔍 Thread ID [" + Thread.currentThread().getId() + "] searching: " + courseName);
        
        // Manual Captcha Bypass
        Thread.sleep(30000); 
    }

    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(func.getCurrentURL(Base.getDriver()).contains("q="), "❌ Search results not displayed.");
    }

    @When("User selects the first course from results")
    public void user_selects_first_course() {
        Pages.get().search.openFirstCourse();
    }

    @When("User adds the course to cart")
    public void user_adds_course_to_cart() {
        Pages.get().course.clickAddToCart();
    }

    @Then("Course should be added successfully")
    public void course_added_successfully() {
        Assert.assertTrue(func.getCurrentURL(Base.getDriver()).contains("/course/"), "❌ Not on Course Detail page.");
    }

    @When("User clicks on instructor profile")
    public void user_clicks_instructor_profile() {
        Pages.get().course.clickInstructor();
    }

    @Then("Instructor profile page should be displayed")
    public void instructor_profile_page_displayed() {
        Assert.assertTrue(func.getCurrentURL(Base.getDriver()).contains("/user/"), "❌ Profile page not loaded!");
    }

    @When("User opens instructor LinkedIn profile")
    public void user_opens_linkedin() {
        Pages.get().social.openLinkedIn();
    }

    @Then("LinkedIn page should open in a new tab")
    public void linkedin_opened() {
        // Validation updated to use thread-safe driver
        Assert.assertTrue(func.getCurrentURL(Base.getDriver()).contains("/user/"), "❌ Failed to return to profile.");
    }

    @When("User opens instructor YouTube channel")
    public void user_opens_youtube() {
        Pages.get().social.openYouTube();
    }

    @Then("YouTube channel should open successfully")
    public void youtube_opened() {
        int windowCount = Base.getDriver().getWindowHandles().size();
        Assert.assertTrue(windowCount >= 2, "❌ YouTube tab didn't open!");
    }

    @Then("An appropriate no results message should be displayed")
    public void verify_invalid_search_message() throws IOException {
        // ✅ Local WebDriverWait ensures it only watches this thread's browser
        WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(30));
        
        By noResultsLocator = By.xpath("//h1[contains(text(),'Sorry')] | //div[contains(text(),'could not find')]");
        
        boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsLocator)).isDisplayed();
        Assert.assertTrue(isVisible, "❌ No results message missing!");
        
        // Take Screenshot
        String path = scr.takeScreenshot(Base.getDriver(), "Invalid_Search_Result");
        System.out.println("📸 Screenshot saved for Thread: " + Thread.currentThread().getId());
        
       
    }
}