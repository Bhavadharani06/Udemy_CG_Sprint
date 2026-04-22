package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.AllFunctionality;

public class WishlistPage {

    WebDriver driver;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= NAVIGATION =================

    public void clickWishlistTab() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//a[contains(@href,'wishlist')]"), 20).click();
    }

    public void clickBrowseCourses() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//a[contains(text(),'Browse courses')]"), 20).click();
    }

    // ================= VALIDATION =================

    public boolean isWishlistEmpty() {
        try {
            return driver.findElement(
                    By.xpath("//h3[contains(text(),'Want to save something')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= SEARCH & FILTER =================

    public void searchCourse(String courseName) {
        WebElement search = AllFunctionality.waitVisible(driver, By.xpath("//input[@placeholder='Search for anything']"), 20);

        search.sendKeys(courseName);
        search.submit();
    }

    public void applyFreeFilter() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//label[contains(.,'Free')]"), 20).click();
    }

    // ================= HOVER + ADD TO WISHLIST 🔥 =================

    public void addCourseToWishlist(String courseName) {

        WebElement course = AllFunctionality.waitVisible(driver, By.xpath("//h3[contains(.,'" + courseName + "')]"), 20);

        // Hover on course 
        Actions act = new Actions(driver);
        act.moveToElement(course).perform();

        // Click heart icon
        WebElement heart = AllFunctionality.waitClickable(driver,
                By.xpath("//h3[contains(.,'" + courseName + "')]/ancestor::div[contains(@class,'course-card')]//button[contains(@aria-label,'wishlist')]"),
                20);

        heart.click();

        System.out.println("Course added to wishlist");
    }

    // ================= BUSINESS LOGIC =================

    public void handleWishlistFlow(String courseName) {

        clickWishlistTab();

        if (isWishlistEmpty()) {

            System.out.println("Wishlist empty → Adding course");

            clickBrowseCourses();
            searchCourse(courseName);
            applyFreeFilter();
            addCourseToWishlist(courseName);

        } else {

            System.out.println("Wishlist already has courses → No action needed");
        }
    }
}
