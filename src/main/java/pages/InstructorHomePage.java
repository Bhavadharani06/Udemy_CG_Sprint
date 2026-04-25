package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class InstructorHomePage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By searchInput = By.name("q");
    private By searchButton = By.xpath("//button[@type='submit']");

    public InstructorHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * This method handles the search regardless of whether 
     * the input is a valid course or random text.
     */
    public void searchCourse(String courseName) {
        try {
            // 1. Wait until search box is present and visible
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));

            // 2. Clear any existing text (important for parallel thread stability)
            element.clear();


            element.sendKeys(courseName);
            System.out.println("✅ Typed into search: " + courseName);

            // 4. Submit the search
            // We use Keys.ENTER first, but if it's stubborn, we click the magnifying glass
            element.sendKeys(Keys.ENTER);

            // 5. Fallback: If URL doesn't update, force click the search button
            try {
                if (!driver.getCurrentUrl().contains("q=")) {
                    driver.findElement(searchButton).click();
                }
            } catch (Exception e) {
                // If button isn't found, we likely already navigated
            }

        } catch (Exception e) {
            System.out.println("❌ Error interacting with search box: " + e.getMessage());
            // Take a screenshot if needed or throw exception to fail the test
            throw new RuntimeException("Could not complete search interaction");
        }
    }

    /**
     * Helper to verify login state (used in Hooks)
     */
    public boolean isUserLoggedIn() {
        try {
            // Check for presence of user profile avatar or 'My Learning'
            return driver.findElements(By.xpath("//a[contains(@href, 'my-courses')]")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}