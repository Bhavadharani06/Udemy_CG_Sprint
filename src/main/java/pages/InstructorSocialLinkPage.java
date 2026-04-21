package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Set;

public class InstructorSocialLinkPage {

    WebDriver driver;
    WebDriverWait wait;

    public InstructorSocialLinkPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openLinkedIn() {
        String parent = driver.getWindowHandle();
        WebElement linkedIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'linkedin.com')]")));
        linkedIn.click();
        
        // Switch to the new LinkedIn tab just to verify, then come back
        switchToNewTab(parent);
        driver.close(); // Close LinkedIn tab
        driver.switchTo().window(parent); // Switch back to Instructor profile
    }

    public void openYouTube() {
        // 1. Refresh the Instructor page to ensure the social links are clickable
        driver.navigate().refresh();
        System.out.println("🔄 Refreshed Instructor page for YouTube link");

        // 2. Use a safe JS click for YouTube
        try {
            By youtubeLocator = By.xpath("//a[contains(@href,'youtube.com')]");
            WebElement youtubeLink = wait.until(ExpectedConditions.presenceOfElementLocated(youtubeLocator));
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", youtubeLink);
            Thread.sleep(1000);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", youtubeLink);
            System.out.println("🎯 YouTube link clicked successfully");
        } catch (Exception e) {
            System.out.println("⚠️ YouTube link not found or could not be clicked: " + e.getMessage());
        }
    }

    // Helper method to handle window switching
    private void switchToNewTab(String parent) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }
}