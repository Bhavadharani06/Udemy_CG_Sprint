package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class InstructorCoursePage {

    WebDriver driver;
    WebDriverWait wait;

    public InstructorCoursePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickAddToCart() {
        try {
            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Add to cart') or contains(.,'Buy')]")));
            addToCart.click();
            System.out.println("✅ Add to Cart clicked");
        } catch (Exception e) {
            System.out.println("⚠️ Add to Cart button not found.");
        }
    }

    public void clickInstructor() {
        // 1. Initial Refresh to sync the DOM
        driver.navigate().refresh();
        System.out.println("🔄 Page refreshed in the new tab.");

        By instructorLocator = By.xpath("//a[contains(@class, 'instructor-link') or contains(@href, '/user/')]");
        
        // 2. Retry loop to handle the StaleElementReferenceException
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement instructor = wait.until(ExpectedConditions.presenceOfElementLocated(instructorLocator));

                // Try to scroll the element into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", instructor);
                
                // Allow the UI to settle after scroll
                Thread.sleep(2000);

                // Try to click using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", instructor);
                
                System.out.println("🎯 Instructor link clicked successfully!");
                return; // Exit the method if successful

            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ Element went stale during interaction, retrying... (" + (attempts + 1) + ")");
                // Small pause before retrying to let the DOM stabilize
                try { Thread.sleep(1000); } catch (InterruptedException ie) {}
            } catch (Exception e) {
                System.out.println("⚠️ Attempting to recover from error: " + e.getMessage());
            }
            attempts++;
        }
        
        throw new RuntimeException("❌ Failed to click instructor after " + attempts + " attempts.");
    }
}