package stepDefinition;

import io.cucumber.java.en.*;
import utility.Pages;

public class MyListSteps {

    // BACKGROUND

    @Given("User navigates to My Learning Page")
    public void user_navigates_to_my_learning_page() {
        Pages.get().homePage.navigateToMyLearning();
        System.out.println("Navigated to My Learning page");
    }

    // NAVIGATE TO MY LISTS

    @When("User navigates to My Lists tab")
    public void user_navigates_to_my_lists_tab() {
        Pages.get().myListPage.clickMyListTab();
    }

    // MAIN FLOW 

    @Then("User handles My List flow with listName {string} and description {string}")
    public void user_handles_my_list_flow(String listName, String desc) throws InterruptedException {
        Pages.get().myListPage.handleMyListFlow(listName, desc);
    }

    // ASSERTION

    @And("User verifies the list is present in My Lists")
    public void user_verifies_list_present() {
        Pages.get().myListPage.assertListPresent();
    }
}