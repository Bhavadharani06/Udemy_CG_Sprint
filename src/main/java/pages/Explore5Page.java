package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Explore5Page {
	
	 WebDriver driver;
	    WebDriverWait wait;
	    JavascriptExecutor js;
	    Actions act;

	    public Explore5Page(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        this.js = (JavascriptExecutor) driver;
	        this.act = new Actions(driver);
	        PageFactory.initElements(driver, this);
	    }
	    
	    String email;

	    // ================================
	    // LOCATORS (Using By for dynamic handling)
	    // ================================

	    By exploreBtn = By.cssSelector("[aria-label='Explore']");
	    By categoryLocator = By.xpath("//div[text()='%s']");
	    By coursesList = By.xpath("//a[contains(@href,'/course/')]");
	    By addToCartBtn = By.xpath("//button[@data-testid='add-to-cart-button']");
	    By goToCartBtn = By.xpath("//span[text()='Go to cart']");
	    By proceedCheckoutBtn = By.xpath("//span[text()='Proceed to Checkout']/..");
	    //By emailField = By.xpath("//input[@type='email']");

	    // ================================
	    // ACTION METHODS
	    // ================================

	    // Open Udemy
	    public void openUdemy(String url) {
	        driver.get(url);
	    }

	    // Click Explore
	    public void clickExplore() {
	        WebElement explore = wait.until(ExpectedConditions.elementToBeClickable(exploreBtn));
	        explore.click();
	    }

	    // Select Category dynamically
	    public void selectCategory(String category) {
	        By locator = By.xpath(String.format("//div[text()='%s']", category));
	        WebElement cat = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        js.executeScript("arguments[0].click();", cat);

	        wait.until(ExpectedConditions.urlContains(category.toLowerCase()));
	    }

	    // Hover on FIRST visible course
	    public void hoverOnFirstCourse() {

	        List<WebElement> courses = wait.until(
	                ExpectedConditions.presenceOfAllElementsLocatedBy(coursesList)
	        );

	        WebElement firstCourse = null;

	        for (WebElement c : courses) {
	            if (c.isDisplayed()) {
	                firstCourse = c;
	                break;
	            }
	        }

	        if (firstCourse == null) {
	            throw new RuntimeException("No visible course found");
	        }

	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstCourse);

	        // Hover
	        act.moveToElement(firstCourse).pause(Duration.ofSeconds(2)).perform();
	    }

	    // Click Add to Cart (popup)
	    public void clickAddToCart() {
	        // 5. Use a dynamic locator to find the button ONLY after hover card is visible
	        By addToCartLocator = By.xpath("//button[@data-testid='add-to-cart-button']");
	        WebElement title = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='add-to-cart-button']"))
	            );
	        
	        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));

	        // 6. Use JS Click to bypass any "Element Click Intercepted" issues
	        js.executeScript("arguments[0].click();", cartBtn);
	        
	        // 7. Optional: Wait for the "Go to Cart" button to appear to confirm success
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Go to cart']")));
	    }

	    // Click Go to Cart
	    public void clickGoToCart() {

	        WebElement goCart = wait.until(
	                ExpectedConditions.elementToBeClickable(goToCartBtn)
	        );

	        js.executeScript("arguments[0].click();", goCart);
	    }

	    // Click Proceed to Checkout
	    public void clickProceedToCheckout() {

	        WebElement checkout = wait.until(
	                ExpectedConditions.elementToBeClickable(proceedCheckoutBtn)
	        );

	        js.executeScript("arguments[0].click();", checkout);
	    }


	 // Change the method signature to accept a String parameter
	    public void enterEmail(String emailValue) {
	        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
	        emailInput.clear();
	        emailInput.sendKeys(emailValue); // emailValue will no longer be null
	    }
	    
	    public boolean isOnLoginPage() {

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@type='email']")
	        )).isDisplayed();
	    }

}
