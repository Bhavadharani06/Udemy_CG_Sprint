package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement searchBox;

    public void searchCourse(String course) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).click();

        searchBox.clear();
        searchBox.sendKeys(course, Keys.ENTER);

        Thread.sleep(5000); // (can improve later)
    }

    public String getSearchText() {
        return searchBox.getAttribute("value");
    }
}