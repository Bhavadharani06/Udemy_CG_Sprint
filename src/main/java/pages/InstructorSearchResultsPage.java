package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class InstructorSearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    public InstructorSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void openFirstCourse() {
        // Find the first course link
        List<WebElement> courses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")));

        if (courses.isEmpty()) {
            throw new RuntimeException("No courses found!");
        }

        String parentWindow = driver.getWindowHandle();
        
        // Click the first course
        courses.get(0).click();
        System.out.println("🖱️ Course clicked. Waiting for the new tab to open...");

        // --- CRITICAL: SWITCH TO NEW TAB ---
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println("🔀 Switched to Course Detail tab: " + driver.getTitle());
                break;
            }
        }
    }
}