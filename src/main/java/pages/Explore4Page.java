package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Explore4Page {
	
	  WebDriver driver;
	    WebDriverWait wait;
	    JavascriptExecutor js;
	    Actions act;
	    
	    private String selectedCourseTitle;
	    String expectedTitle;
	    
	    // CONSTRUCTOR

	    public Explore4Page(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        this.js = (JavascriptExecutor) driver;
	        this.act = new Actions(driver);
	        PageFactory.initElements(driver, this); 
	    }

	    // LOCATORS 

	    @FindBy(css = "[aria-label='Explore']")
	    private WebElement exploreBtn;

	    @FindBy(xpath = "//span[.='Trending']")
	    private WebElement trendingTab;

	    @FindBy(xpath = "//a[contains(@href,'/course/')]")
	    private List<WebElement> courseLinks;

	    @FindBy(xpath = "//h1")
	    private WebElement courseTitle;

	    @FindBy(id = "subscriptionTrialOfferButton")
	    private WebElement subscribeBtn;

	    @FindBy(name = "email")
	    private WebElement emailField;
	    
	 // LOCATORS
	    @FindBy(xpath = "//h2[contains(text(),'Trending')]")
	    private WebElement trendingHeader;

	    @FindBy(xpath = "//button[contains(@data-purpose, 'subscribe')] | //button[contains(.,'Try Personal Plan')]")
	    private WebElement subscribeButton;
	    
	
	    //  ACTION METHODS 

	    public void clickTrending() {
	        // Scroll to the Trending section if it's further down the page
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", trendingHeader);
//	        wait.until(ExpectedConditions.visibilityOf(trendingHeader));
	        WebElement trending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Trending']"))
	        );
	        js.executeScript("arguments[0].click();", trending);
	    }
	    
	    public void selectFirstCourse() {
	        List<WebElement> courses = wait.until(
	                ExpectedConditions.presenceOfAllElementsLocatedBy(
	                        By.xpath("//a[contains(@href,'/course/')]")
	                )
	        );

	        WebElement firstCourse = null;

	        for (WebElement c : courses) {
	            if (c.isDisplayed()) {
	                firstCourse = c;
	                break;
	            }
	        }

	        if (firstCourse == null) {
	            throw new RuntimeException("No course found");
	        }

	        String fullText = firstCourse.getText().trim();
	        expectedTitle = fullText.split("\n")[0].trim();

	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstCourse);

	        act.moveToElement(firstCourse).pause(Duration.ofSeconds(1)).click().perform();

	        switchToNewTab();
	    }
	    
	    private void switchToNewTab() {
	        String parent = driver.getWindowHandle();

	        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

	        for (String win : driver.getWindowHandles()) {
	            if (!win.equals(parent)) {
	                driver.switchTo().window(win);
	                break;
	            }
	        }
	    }

	    //  VALIDATION METHODS 

	    public boolean isOnCoursePage() {
	    	wait.until(ExpectedConditions.visibilityOf(courseTitle));
	        return driver.getCurrentUrl().contains("course");
	    }

//	    public boolean isTitleMatching() {
//	    	String actualTitle = courseTitle.getText().trim();
//
//	        System.out.println("Expected: " + expectedTitle);
//	        System.out.println("Actual: " + actualTitle);
//
//	        return actualTitle.equalsIgnoreCase(expectedTitle);
//	    }
//	    
	    
	    public boolean validateCourseTitle() {
	        WebElement title = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))
	        );

	        String actualTitle = title.getText().trim();

	        System.out.println("Expected: " + expectedTitle);
	        System.out.println("Actual: " + actualTitle);

	        return actualTitle.equalsIgnoreCase(expectedTitle);
	    }
	    
	    public void clickSubscribe() {
	        WebElement btn = wait.until(ExpectedConditions.visibilityOf(subscribeBtn));
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
	        js.executeScript("arguments[0].click();", btn);
	    }

	    public boolean isOnAuthPage() {
	        wait.until(ExpectedConditions.urlContains("/join/"));
	        String url = driver.getCurrentUrl();

	        return url.contains("/join/") ||  emailField.isDisplayed() ;
	    }

}
