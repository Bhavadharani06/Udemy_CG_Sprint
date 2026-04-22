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
            // Using Presence instead of Visibility to handle elements that load behind overlays
            WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(.,'Add to cart') or contains(.,'Buy')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
            System.out.println("✅ Add to Cart clicked via JS");
        } catch (Exception e) {
            System.out.println("⚠️ Add to Cart button not found (it might already be in cart).");
        }
    }

    public void clickInstructor() {
        try {
            driver.navigate().refresh();
            By instructorLocator = By.xpath("//a[contains(@class, 'instructor-link') or contains(@href, '/user/')]");
            WebElement instructor = wait.until(ExpectedConditions.elementToBeClickable(instructorLocator));
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", instructor);
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", instructor);
            System.out.println("🎯 Instructor link clicked!");
        } catch (Exception e) {
            System.out.println("⚠️ Instructor click failed, trying secondary locator...");
            // Secondary attempt if first locator fails
            ((JavascriptExecutor) driver).executeScript("document.querySelector('a[href*=\"/user/\"]').click();");
        }
    }
}