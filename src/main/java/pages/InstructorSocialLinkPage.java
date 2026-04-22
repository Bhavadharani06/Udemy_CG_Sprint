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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openLinkedIn() {
        handleSocialClick("//a[contains(@href,'linkedin.com')]", "LinkedIn");
    }

    public void openYouTube() {
        handleSocialClick("//a[contains(@href,'youtube.com')]", "YouTube");
    }

    private void handleSocialClick(String xpath, String platform) {
        try {
            String parent = driver.getWindowHandle();
            WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            
            // Wait for tab to appear
            wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
            System.out.println("🔗 " + platform + " opened.");
            
            // Just switch to verify and switch back (don't close yet to avoid session issues)
            for (String win : driver.getWindowHandles()) {
                if (!win.equals(parent)) {
                    driver.switchTo().window(win);
                }
            }
            driver.switchTo().window(parent); 
        } catch (Exception e) {
            System.out.println("⚠️ " + platform + " link was not available or failed to click.");
        }
    }
}