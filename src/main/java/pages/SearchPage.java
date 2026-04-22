package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Get all courses dynamically
    public List<WebElement> getCourses() {
        return wait.until(d -> {
            List<WebElement> list = d.findElements(
                    By.xpath("//a[contains(@href,'/course/')]")
            );
            return list.size() > 0 ? list : null;
        });
    }

    //Get course title by index
    public String getCourseTitle(int index) {
        List<WebElement> courses = getCourses();
        return courses.get(index).getText().trim();
    }

    //Click first course (with tab handling)
    public void openFirstCourse() {

        List<WebElement> courses = driver.findElements(
                By.xpath("//a[contains(@href,'/course/') and contains(@class,'ud-link-neutral')]")
        );

        String parent = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", courses.get(0));
        courses.get(0).click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
            }
        }
    }
    
    public void openCourseByIndex(int index) {

        List<WebElement> courses = driver.findElements(
                By.xpath("//a[contains(@href,'/course/')]")
        );

        String parent = driver.getWindowHandle();

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", courses.get(index));

        courses.get(index).click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
            }
        }
    }
    
}