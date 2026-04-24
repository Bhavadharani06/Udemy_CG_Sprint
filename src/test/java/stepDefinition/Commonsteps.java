//package stepDefinition;
//
//import stepDefinition.Hooks;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import utility.Base;
//import utility.Pages;
//
//public class Commonsteps extends Base {
//
//    // Global variable to store the URL for validation in other step classes
//    public static String expectedURL;
//
//
//	    @Given("User navigates to {string}")
//	    public void user_navigates_to(String url) {
//	        Base.getDriver().get(url);
//	    }
//
//	    @When("User clicks on Explore menu")
//	    public void user_clicks_on_explore_menu() {
//	    	// 1. Wait for Cloudflare via the common page logic
//	    	Pages.get().common.waitForCloudflareToDisappear();
//	        // 2. Perform the click
//	    	Pages.get().common.clickExplore();
//	    }
//
//	    @When("User chooses {string}")
//	    public void user_chooses(String category) throws InterruptedException {
//	    	Pages.get().common.selectCategory(category);
//	    }
//
//	    @When("User selects subcategory {string}")
//	    public void user_selects_subcategory(String subCategory) throws InterruptedException {
//	    	Pages.get().common.selectSubCategory(subCategory);
//	    }
//	    
//	    @When("User clicks on the first available course")
//	    public void user_clicks_on_the_first_available_course() throws InterruptedException {
//	    	Thread.sleep(4000);
//	    	expectedURL = Pages.get().common.clickFirstCourse();
//	    }
//}