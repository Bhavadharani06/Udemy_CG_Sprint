package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class InstructorHomePage {

    WebDriver driver;
    WebDriverWait wait;

    public InstructorHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void searchCourse(String course) {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );

        searchBox.clear();
        searchBox.sendKeys(course);
        searchBox.sendKeys(Keys.ENTER);
    }
}