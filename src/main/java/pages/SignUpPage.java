package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {
	
    // ================= ELEMENTS =================

    @FindBy(xpath = "//a[contains(@href,'/join/signup')]")
    private WebElement signUpBtn;
    
    @FindBy(xpath = "//span[.='Log in']")
    private WebElement logInBtn;

    @FindBy(name = "full-name")
    private WebElement nameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(xpath = "//span[.='Continue']")
    private WebElement continueBtn;

    @FindBy(xpath = "//button[.//span[text()='Sign up']]")
    private WebElement finalSignupBtn;
    
    @FindBy(xpath = "//button[.//span[text()='Log in']]")
    private WebElement finalLogInBtn;

    // ================= GETTERS =================

    public WebElement getSignUpBtn() {
        return signUpBtn;
    }
    
    public WebElement getLogInBtn() {
        return logInBtn;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getContinueBtn() {
        return continueBtn;
    }

    public WebElement getFinalSignupBtn() {
        return finalSignupBtn;
    }
    
    public WebElement getFinalLogInBtn() {
        return finalLogInBtn;
    }

    // ================= BASIC ACTION METHODS =================

    public void clickSignUp() {
        signUpBtn.click();
    }
    
    public void clickLogIn() {
        logInBtn.click();
    }

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public void clickFinalSignUp() {
        finalSignupBtn.click();
    }
    
    public void clickFinalLogIn() {
        finalLogInBtn.click();
    }

    // ================= MANUAL STEPS =================

    public void waitForCaptcha() {
        System.out.println("👉 Please complete CAPTCHA manually...");
        try {
            Thread.sleep(40000); // 40 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForOTP() {
        System.out.println("👉 Please enter OTP manually...");
        try {
            Thread.sleep(30000); // 30 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ================= BUSINESS LOGIC =================

    // Step 1: Fill signup form
    public void signup(String name, String email) {
        clickSignUp();
        waitForCaptcha();   // manual

        enterName(name);
        enterEmail(email);
        clickContinue();
    }

    // Step 2: Complete signup after OTP
    public void completeSignup() {
        waitForOTP();       // manual
        clickFinalSignUp();
    }

    // ================= COMPLETE FLOW =================

    public void completeSignupFlow(String name, String email) {
        signup(name, email);
        completeSignup();
    }
    
    
    // Step 1: Fill signup form
    public void logIn(String email) {
        clickLogIn();
        waitForCaptcha();   // manual

        enterEmail(email);
        clickContinue();
    }

    // Step 2: Complete signup after OTP
    public void completeLogIn() {
        waitForOTP();       // manual
        clickFinalLogIn();
    }

    // ================= COMPLETE FLOW =================

    public void completeLogInFlow(String email) {
        logIn(email);
        completeLogIn();
    }
    
    
}