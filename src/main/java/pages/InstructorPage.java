package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InstructorPage {

    WebDriver driver;

    public InstructorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    public WebElement instructorHeader;

    // ✅ Check if new page opened
    public boolean isProfileUrl() {
        return driver.getCurrentUrl().contains("user");
    }

    // ✅ Validate instructor name (URL case)
    public boolean isInstructorMatching(String expectedName) {
        return instructorHeader.getText().toLowerCase()
                .contains(expectedName.split(" ")[0].toLowerCase());
    }
    
    public String getInstructorHeader() {
        return instructorHeader.getText();
    }

    
    public boolean isInstructorVisibleOnCourse(String name) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        String firstName = name.split(" ")[0];

        java.util.List<WebElement> list = driver.findElements(
                By.xpath("//a[contains(text(),'" + firstName + "')]")
        );

        return !list.isEmpty();
    }
}