package pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.AllFunctionality;

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

public class Explore2Page {

    WebDriver driver;
    WebDriverWait wait;
   JavascriptExecutor js;
//    Actions actions;

    public Explore2Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
         this. js = (JavascriptExecutor) driver;
//        actions = new Actions(driver);
    }
    
// // LOCATORS
//    @FindBy(xpath = "//a[contains(@href,'/course/')]")
//    private List<WebElement> courseCards;
//
//    // The title inside the course card on the search/category page
//    private String courseTitleLocator = ".//h3 | .//div[contains(@class,'course-title')]";

    
    // Verify courses
    public boolean isCoursesDisplayed() {
        List<WebElement> courses = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")
            )
        );
        return courses.size() > 0;
    }

    public String getFirstCourseTitle() {

        List<WebElement> courseList = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")
            )
        );

        return courseList.get(0).getText().split("\n")[0];
    }

    public void clickFirstCourse() {

        List<WebElement> courseList = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")
            )
        );

        WebElement firstCourse = courseList.get(0);

        js.executeScript("arguments[0].scrollIntoView(true);", firstCourse);

        try {
            firstCourse.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", firstCourse);
        }
    }
    // Switch tab
    public void switchToNewTabIfPresent() {
        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Get course title
    public String getCourseTitle() {
        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))
        );
        return title.getText().trim();
    }
}
