package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Explore3Page {
	
	

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;

    public Explore3Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    // LOCATORS
    @FindBy(xpath = "//a[contains(@href,'/course/')]")
    private List<WebElement> courseCards;
    @FindBy(xpath = "//select[@name='sort' and @form='filter-form']")
    WebElement sortDropdown;

    @FindBy(xpath = "//label[contains(.,'4.0')]")
    WebElement rating4;

    @FindBy(xpath = "//span[.='Video Duration']/ancestor::button")
    WebElement videoDuration;

    @FindBy(xpath = "//span[.='Topic']/ancestor::button")
    WebElement topic;

    @FindBy(xpath = "//label[contains(., 'Painting')]")
    WebElement painting;

    @FindBy(xpath = "//span[.='Subcategory']/ancestor::button")
    WebElement subCategoryFilter;

    @FindBy(xpath = "//label[contains(., 'Arts & Crafts')]")
    WebElement artsCrafts;

    @FindBy(xpath = "//span[.='Level']/ancestor::button")
    WebElement level;

    @FindBy(xpath = "//input[@value='all']/parent::*")
    WebElement allLevels;

    @FindBy(xpath = "//span[.='Language']/ancestor::button")
    WebElement language;

    @FindBy(xpath = "//input[@value='en']/following-sibling::*")
    WebElement english;

    @FindBy(xpath = "//span[.='Price']/ancestor::button")
    WebElement price;

    @FindBy(xpath = "//input[@value='price-free']")
    WebElement free;

    @FindBy(xpath = "//a[contains(@href,'/course/')]")
    List<WebElement> courses;

   
    // ACTION METHODS
    
    public void selectCategory(String category) {

        WebElement categoryElement = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='" + category + "']")
            )
        );

        js.executeScript("arguments[0].click();", categoryElement);

        // ADD THIS (CRITICAL FIX)
        wait.until(ExpectedConditions.urlContains(category.toLowerCase()));

        // Optional small wait for UI stabilization
        try { Thread.sleep(3000); } catch (Exception e) {}
    }
    
    public void scrollToFilters() {

        WebElement filter = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Topic']")
            )
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", filter);
    }
    
    public void selectSortOption() {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        actions.sendKeys(Keys.ARROW_DOWN)
               .sendKeys(Keys.ARROW_DOWN)
               .sendKeys(Keys.ENTER)
               .perform();
    }

    public void selectRating() {
        wait.until(ExpectedConditions.elementToBeClickable(rating4)).click();
    }

    public void selectVideoDuration() {
        // Click "Show more" first
        WebElement showMore = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@aria-label,'Show more')]")
            )
        );
        js.executeScript("arguments[0].click();", showMore);

        // Now click Video Duration
        wait.until(ExpectedConditions.elementToBeClickable(videoDuration));
        js.executeScript("arguments[0].click();", videoDuration);
    }
    public void selectTopic() {
        wait.until(ExpectedConditions.elementToBeClickable(topic));
        js.executeScript("arguments[0].click();", topic);

        wait.until(ExpectedConditions.elementToBeClickable(painting));
        js.executeScript("arguments[0].click();", painting);
    }

    public void selectSubCategoryFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(subCategoryFilter));
        js.executeScript("arguments[0].click();", subCategoryFilter);

        wait.until(ExpectedConditions.elementToBeClickable(artsCrafts));
        js.executeScript("arguments[0].click();", artsCrafts);
    }

    public void selectLevel() {
        wait.until(ExpectedConditions.elementToBeClickable(level));
        js.executeScript("arguments[0].click();", level);

        wait.until(ExpectedConditions.elementToBeClickable(allLevels));
        js.executeScript("arguments[0].click();", allLevels);
    }

    public void selectLanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(language));
        js.executeScript("arguments[0].click();", language);

        wait.until(ExpectedConditions.elementToBeClickable(english)).click();
    }

    public void selectPrice() {
        wait.until(ExpectedConditions.elementToBeClickable(price));
        js.executeScript("arguments[0].click();", price);

        wait.until(ExpectedConditions.elementToBeClickable(free));
        js.executeScript("arguments[0].click();", free);
    }

//    public void verifyCoursesDisplayed() {
//
//        List<WebElement> courseList = wait.until(
//            ExpectedConditions.numberOfElementsToBeMoreThan(
//                By.xpath("//a[contains(@href,'/course/')]"), 0)
//        );
//
//        if (courseList.size() == 0) {
//            throw new RuntimeException("No courses displayed");
//        } else {
//            System.out.println("Courses are displayed");
//        }
//    }
    
    public boolean verifyCoursesDisplayed() {

        try {
            // Wait for either courses OR "no results"
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'/course/')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'No results')]"))
            ));

            List<WebElement> courses = driver.findElements(By.xpath("//a[contains(@href,'/course/')]"));

            if (courses.size() > 0) {
                System.out.println("Courses found: " + courses.size());
                return true;
            } else {
                System.out.println("No courses found after filters");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
    
//
//    public String clickRandomCourse() {
//
//        wait.until(ExpectedConditions.visibilityOfAllElements(courses));
//
//        int index = new Random().nextInt(courses.size());
//        WebElement course = courses.get(index);
//
//        js.executeScript("arguments[0].scrollIntoView(true);", course);
//
//        String url = course.getAttribute("href");
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(course)).click();
//        } catch (Exception e) {
//            js.executeScript("arguments[0].click();", course);
//        }
//
//        return url;
//    }
    public String clickRandomCourse() {

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector("div[data-purpose='loading-spinner']")
        ));

        // Wait for courses
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//a[contains(@href,'/course/')]")
        ));

        // Scroll to TOP (force)
        js.executeScript("window.scrollTo(0,0)");

        try { Thread.sleep(2000); } catch (Exception e) {}

        // Fetch fresh elements
        List<WebElement> courseList = driver.findElements(
            By.xpath("//a[contains(@href,'/course/')]")
        );

        if(courseList.size() == 0){
            throw new RuntimeException("No courses found after filtering");
        }

        WebElement firstCourse = courseList.get(0);

        // Scroll properly
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstCourse);

        String url = firstCourse.getAttribute("href");

        // Wait clickable
        wait.until(ExpectedConditions.elementToBeClickable(firstCourse));

        try {
            firstCourse.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", firstCourse);
        }

        return url;
    }

}
