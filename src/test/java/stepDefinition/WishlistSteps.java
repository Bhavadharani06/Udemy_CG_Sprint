package stepDefinition;

import io.cucumber.java.en.*;
import utility.Pages;

public class WishlistSteps {

    // BACKGROUND

    @Given("User is on My Learning page")
    public void user_is_on_my_learning_page() {
        Pages.get().homePage.navigateToMyLearning();
        System.out.println("Navigated to My Learning page");
    }

    // NAVIGATE TO WISHLIST 

    @When("User navigates to Wishlist tab")
    public void user_navigates_to_wishlist_tab() {
        Pages.get().wishlistPage.clickWishlistTab();
    }

    // MAIN FLOW

    @Then("User manages wishlist based on availability")
    public void user_manages_wishlist_based_on_availability() throws InterruptedException {
        Pages.get().wishlistPage.handleWishlistFlow();
    }

    // ASSERTIONS

    @Then("User verifies wishlist is not empty")
    public void user_verifies_wishlist_not_empty() {
        Pages.get().wishlistPage.assertWishlistNotEmpty();
    }

    @Then("User verifies course {string} is present in wishlist")
    public void user_verifies_course_in_wishlist(String courseName) {
        Pages.get().wishlistPage.assertCourseInWishlist(courseName);
    }
}