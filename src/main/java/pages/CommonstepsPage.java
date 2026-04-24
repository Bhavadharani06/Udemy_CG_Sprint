package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonstepsPage {

	    WebDriver driver;
	    WebDriverWait wait;

	    //public static String expectedURL;
	    
	    
	    public CommonstepsPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        PageFactory.initElements(driver, this);
	    }
	    
	    
	    public void waitForCloudflareToDisappear() {
	        try {
	            // Wait for the Udemy logo or a main nav item to appear instead of title text
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Udemy']")));
	        } catch (Exception e) {
	            System.out.println("Cloudflare check timed out or logo not found.");
	        }
	    }

	    public void clickExplore() {
	        WebElement explore = wait.until(
	            ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Explore']"))
	        );
	        explore.click();
	    }

	    	    public void selectCategory(String category) throws InterruptedException {
	        WebElement cat = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[text()='" + category + "']")
	            )
	        );
	        cat.click();
	        Thread.sleep(10000);
	    }
	    
//	    public void selectCategory(String category) {
//
//	        // Scroll to top
//	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
//
//	        // Wait for Explore button
//	        WebElement explore = wait.until(
//	            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-label='Explore']"))
//	        );
//
//	        // Hover on Explore
//	        Actions actions = new Actions(driver);
//	        actions.moveToElement(explore).perform();
//
//	        // Wait for dropdown menu
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(
//	            By.xpath("//div[contains(@class,'ud-mega-menu')]")
//	        ));
//
//	        // ✅ FIXED locator (SPAN not DIV)
//	        By categoryLocator = By.xpath("//span[normalize-space()='" + category + "']");
//
//	        WebElement cat = wait.until(
//	            ExpectedConditions.visibilityOfElementLocated(categoryLocator)
//	        );
//
//	        // Click using JS
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cat);
//	    }
//	    

	    public void selectSubCategory(String subCategory) {

	        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	        // STEP 1: wait for ANY submenu panel to load
	        wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//span[text()='" + subCategory + "']")
	        ));

	        // STEP 2: now search inside visible UI//span[text()='Web Development']
	        By subCategoryLocator = By.xpath("//*[span[text()='" + subCategory + "']]");

	        WebElement sub = wait.until(
	            ExpectedConditions.elementToBeClickable(subCategoryLocator)
	        );

	        // STEP 3: scroll into view
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", sub);

	        // STEP 4: JS click (IMPORTANT for Udemy)
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", sub);
	    }
	    
	    public String clickFirstCourse() {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        // Wait for loader to disappear
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.cssSelector("div[data-purpose='loading-spinner']")
	        ));

	        // Wait for courses
	        List<WebElement> courses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.xpath("//a[contains(@href,'/course/')]")
	        ));

	        // Scroll to top
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

	        // Re-fetch elements
	        courses = driver.findElements(By.xpath("//a[contains(@href,'/course/')]"));

	        // 5️⃣ Get first course
	        WebElement firstCourse = courses.get(0);

	        //  ADD THIS LINE (VERY IMPORTANT)
	        String url = firstCourse.getAttribute("href");

	        wait.until(ExpectedConditions.elementToBeClickable(firstCourse));

	        firstCourse.click();

	        return url; 
	    }

}
