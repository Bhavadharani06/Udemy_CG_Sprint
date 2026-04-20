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
    public void openFirstCourse() throws InterruptedException {

        List<WebElement> courses = getCourses();

        String parent = driver.getWindowHandle();

        WebElement firstCourse = courses.get(0);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", firstCourse
        );

        Thread.sleep(1000);

        firstCourse.click();

        Thread.sleep(3000);

        // Switch to new tab if opened
        for (String w : driver.getWindowHandles()) {
            if (!w.equals(parent)) {
                driver.switchTo().window(w);
                break;
            }
        }
    }
}