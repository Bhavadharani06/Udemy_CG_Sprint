package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InstructorPage {

    WebDriver driver;

    public InstructorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Get all h1 elements (safe approach)
    @FindBy(xpath = "//h1")
    private List<WebElement> headers;

    // Get correct instructor name from profile page
    public String getInstructorHeader() {

        for (WebElement h : headers) {
            String text = h.getText().trim();

            // Ignore unwanted headings
            if (text.length() > 3 && 
                !text.equalsIgnoreCase("Instructor") &&
                !text.toLowerCase().contains("course")) {

                return text;
            }
        }
        return "";
    }

    // Check if navigated to instructor profile page
    public boolean isProfileUrl() {
        return driver.getCurrentUrl().contains("user");
    }

    // Validate instructor name (first name match)
    public boolean isInstructorMatching(String expectedName) {

        String actualName = getInstructorHeader();

        String expectedKey = expectedName
                                .replace("Dr.", "")
                                .trim()
                                .split(" ")[0]
                                .toLowerCase();

        return actualName.toLowerCase().contains(expectedKey);
    }

    // For scroll-based validation (no navigation case)
    public boolean isInstructorVisibleOnCourse(String name) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        String firstName = name
                                .replace("Dr.", "")
                                .trim()
                                .split(" ")[0];

        List<WebElement> list = driver.findElements(
                By.xpath("//a[contains(text(),'" + firstName + "')]")
        );

        return !list.isEmpty();
    }
}