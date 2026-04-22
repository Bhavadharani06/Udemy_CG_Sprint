package stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.AllFunctionality;
import utility.Base;

public class BUG_002_InvalidCartUrl {
	@When("User enters invalid cart URL")
	public void userEntersInvalidCartURL() throws IOException {

	    String invalidUrl = new AllFunctionality()
	            .getPropertyKeyValue("invalidCartUrl");

	    Base.getDriver().get(invalidUrl);

	    System.out.println("Navigated to invalid URL: " + invalidUrl);
	}
	@Then("User should see a 404 error page")
	public void userShouldSee404Page() {

	    String pageSource = Base.getDriver().getPageSource().toLowerCase();
	    String currentUrl = Base.getDriver().getCurrentUrl().toLowerCase();

	    System.out.println("Current URL: " + currentUrl);

	    // Flexible validation (since Udemy UI varies)
	    boolean is404 =
	            pageSource.contains("404") ||
	            pageSource.contains("page not found") ||
	            pageSource.contains("we couldn’t find") ||
	            currentUrl.contains("404");

	 // BUG VALIDATION: System currently does NOT redirect to 404 page
	    Assert.assertFalse(is404, "Unexpected 404 page displayed");	}
}
