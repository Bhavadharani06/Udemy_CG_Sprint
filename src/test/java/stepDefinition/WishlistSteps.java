package stepDefinition;

import io.cucumber.java.en.*;
import utility.Pages;

public class WishlistSteps {

    @When("User navigates to Wishlist")
    public void navigate_wishlist() {
        Pages.wishlistPage.clickWishlistTab();
    }

    @Then("User manages wishlist based on availability")
    public void manage_wishlist() {

        String courseName = "Java"; // later from Excel

        Pages.wishlistPage.handleWishlistFlow(courseName);
    }
}