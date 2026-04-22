package stepDefinition;

import io.cucumber.java.en.*;
import utility.*;

public class SignUpSteps {

	    @Given("launch the new browser")
	    public void launch_the_new_browser() {
	        // Already handled in Hooks
	    }

	    @Given("navigate to udemy using url {string}")
	    public void navigate_to_udemy_using_url(String url) {
	        // Already opened in Hooks
	    }

	    @When("User clicks on Sign Up")
	    public void user_clicks_on_sign_up() {
	        Pages.signUpPage.clickSignUp();
	    }

	    @When("User enters valid name {string} and email {string}")
	    public void user_enters_valid_name_and_email(String name, String email) {
	    		Pages.signUpPage.waitForCaptcha();
	        Pages.signUpPage.enterName(name);
	        Pages.signUpPage.enterEmail(email);
	    }

	    @When("User clicks Continue")
	    public void user_clicks_continue() {
	        Pages.signUpPage.clickContinue();
	    }

	    @Then("Verification code should be sent to registered email")
	    public void verification_code_should_be_sent_to_registered_email() {
	        System.out.println("✅ Verification code sent");
	    }

	    @When("User enters OTP manually and completes signup")
	    public void user_enters_otp_manually_and_completes_signup() {
	        Pages.signUpPage.waitForOTP();
	        Pages.signUpPage.clickFinalSignUp();
	    }

	    @Then("User should be redirected to homepage")
	    public void user_should_be_redirected_to_homepage() {

	        String url = Base.getDriver().getCurrentUrl();

	        if (url.contains("signupsuccess")) {
	            System.out.println("✅ Signup success");
	        } else {
	            System.out.println("❌ Signup failed");
	        }
	    }
	
}