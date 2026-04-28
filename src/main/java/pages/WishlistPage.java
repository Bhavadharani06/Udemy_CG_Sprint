package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.AllFunctionality;

import java.time.Duration;
import java.util.List;

public class WishlistPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // LOCATORS

    // Wishlist tab in My Learning nav
    private By wishlistTab     = By.xpath("//a[contains(@href,'wishlist')]");

    // Empty wishlist — from devtools: <span class="ud-btn-label">Browse courses now</span> inside <a href="/">
    private By emptyMsg        = By.xpath("//span[contains(@class,'ud-btn-label') and contains(text(),'Browse courses now')]");

    // Browse courses now button — the <a> tag wrapping the span (from devtools: <a href="/" class="ud-btn...empty-state--cta...">)
    private By browseCourseBtn = By.xpath("//a[contains(@class,'empty-state--cta')]");

    // Course card image — hover this to trigger the quick view popup
    private By courseCardImage = By.xpath("(//div[contains(@class,'course-card_image-container') or contains(@class,'course-card--image-container') or contains(@class,'course-card_image')])[1]");

    // Heart button — from devtools: <button aria-label="Wishlist" ...>
    private By heartBtn        = By.xpath("(//button[@aria-label='Wishlist'])[1]");

    // Wishlist courses after adding
    private By wishlistCourses = By.xpath("//h3[@data-purpose='course-title-url']");

    // NAVIGATION

    public void clickWishlistTab() {
        AllFunctionality.waitClickable(driver, wishlistTab, 20).click();
        System.out.println("Clicked Wishlist tab");
    }

    public void clickBrowseCourses() {
        // JS click to avoid interception issues on the anchor tag
        WebElement btn = AllFunctionality.waitClickable(driver, browseCourseBtn, 20);
        js.executeScript("arguments[0].click();", btn);
        System.out.println("Clicked Browse Courses Now");
    }

 // VALIDATION (RETURN BOOLEAN)

    public boolean isWishlistEmpty() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(emptyMsg));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getWishlistCourses() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(wishlistCourses));
            return driver.findElements(wishlistCourses);
        } catch (Exception e) {
            return List.of();
        }
    }

    public int getWishlistCourseCount() {
        return getWishlistCourses().size();
    }

    public boolean isWishlistNotEmpty() {
        return getWishlistCourseCount() > 0;
    }

    public boolean isCourseInWishlist(String courseName) {
        for (WebElement course : getWishlistCourses()) {
            if (course.getText().toLowerCase().contains(courseName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    // HOVER + HEART ICON

    public void hoverAndAddToWishlist() throws InterruptedException {
        // Wait for homepage to load
        Thread.sleep(2000);

        // Scroll down to reveal course cards
        js.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(1500);

        // Wait for first course card image
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(courseCardImage));

        // Scroll into center view
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        Thread.sleep(1000);

        // Hover over course card — triggers quick view popup with heart button
        new Actions(driver).moveToElement(card).perform();
        Thread.sleep(2000);
        System.out.println("Hovering over course card image");

        // Heart button appears inside quick view popup — aria-label="Wishlist"
        WebElement heart = wait.until(ExpectedConditions.elementToBeClickable(heartBtn));
        js.executeScript("arguments[0].click();", heart);
        Thread.sleep(1000);
        System.out.println("Heart icon clicked — course added to wishlist");
    }

    // NAVIGATE BACK TO WISHLIST

    public void navigateBackToWishlist() throws InterruptedException {
        driver.get("https://www.udemy.com/home/my-courses/wishlist/");
        Thread.sleep(2000);
        System.out.println("Navigated back to Wishlist page");
    }

    // MAIN FLOW

    public void handleWishlistFlow() throws InterruptedException {
            System.out.println("Wishlist is empty → browsing to add a course");

            // Step 1: Click Browse courses now → lands on homepage
            clickBrowseCourses();

            // Step 2: Hover first course card and click heart icon
            hoverAndAddToWishlist();

            // Step 3: Go back to wishlist directly
            navigateBackToWishlist();
        }
}