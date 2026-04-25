package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.AllFunctionality;

import java.time.Duration;
import java.util.List;

public class ArchivePage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public ArchivePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // @FindBy LOCATORS

    @FindBy(xpath = "//a[contains(@href,'archived')]")
    private WebElement archivedTab;

    // Empty archive message
    @FindBy(xpath = "//h3[contains(text(),'Focus on only the courses')]")
    private WebElement emptyArchiveText;

    // "Go to the All Courses tab" link when archive is empty
    @FindBy(xpath = "//a[contains(@href,'/home/my-courses') and contains(.,'Go to the All Courses')]")
    private WebElement goToAllCoursesLink;

    // Archive button in 3 dots dropdown (All Courses tab)
    @FindBy(xpath = "//button[contains(.,'Archive')]")
    private WebElement archiveBtn;

    // Unarchive button in 3 dots dropdown (Archived tab)
    @FindBy(xpath = "//button[contains(.,'Unarchive')]")
    private WebElement unarchiveBtn;

    // By locators for dynamic waits
    private By firstThreeDots    = By.xpath("(//button[contains(@id,'dropdown-trigger')])[1]");
    private By archivedCourses   = By.xpath("//h3[@data-purpose='course-title-url']");
    private By emptyArchiveBy    = By.xpath("//h3[contains(text(),'Focus on only the courses')]");

    // NAVIGATION

    public void clickArchivedTab() {
        AllFunctionality.waitClickable(driver, By.xpath("//a[contains(@href,'archived')]"), 20).click();
        System.out.println("Clicked Archived tab");
    }

    public void clickGoToAllCourses() {
        AllFunctionality.waitClickable(driver,
            By.xpath("//a[contains(@href,'/home/my-courses') and contains(.,'Go to the All Courses')]"), 20).click();
        System.out.println("Clicked Go to All Courses tab");
    }

    // VALIDATION 

    public boolean isArchiveEmpty() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emptyArchiveBy));
            System.out.println("Archive is EMPTY");
            return true;
        } catch (Exception e) {
            System.out.println("Archive has courses");
            return false;
        }
    }

    public int getArchivedCourseCount() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(archivedCourses));
            return driver.findElements(archivedCourses).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstArchivedCourseTitle() {
        try {
            List<WebElement> courses = driver.findElements(archivedCourses);
            if (!courses.isEmpty()) return courses.get(0).getText().trim();
        } catch (Exception e) {}
        return "";
    }

    // THREE DOTS

    public void clickFirstCourseDots() throws InterruptedException {
        WebElement dots = wait.until(ExpectedConditions.elementToBeClickable(firstThreeDots));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dots);
        Thread.sleep(500);
        dots.click();
        System.out.println("Clicked 3 dots on first course");
    }

    // ARCHIVE / UNARCHIVE

    public void clickArchive() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(archiveBtn)).click();
        Thread.sleep(1500);
        System.out.println("Clicked Archive");
    }

    public void clickUnarchive() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(unarchiveBtn)).click();
        Thread.sleep(1500);
        System.out.println("Clicked Unarchive");
    }

    // ASSERTIONS

    public void assertArchiveHasCourses() {
        int count = getArchivedCourseCount();
        if (count > 0)
            System.out.println("PASS: Archive has " + count + " course(s) after archiving");
        else
            System.out.println("FAIL: Archive is still empty after archiving");
    }

    public void assertArchiveIsEmpty() {
        if (isArchiveEmpty())
            System.out.println("PASS: Archive is empty after unarchiving");
        else
            System.out.println("FAIL: Archive still has courses after unarchiving");
    }

    // MAIN FLOW

    public void handleArchiveFlow() throws InterruptedException {

        if (isArchiveEmpty()) {

            // ── EMPTY: go to All Courses → 3 dots first course → Archive ──
            System.out.println("Archive empty → archiving first course from All Courses");

            clickGoToAllCourses();
            Thread.sleep(2000);

            clickFirstCourseDots();
            Thread.sleep(1000);

            clickArchive();

            // Come back to Archived tab
            clickArchivedTab();
            Thread.sleep(1500);

            // Assert course is now in archive
            assertArchiveHasCourses();

        } else {

            // ── HAS COURSES: 3 dots → Unarchive ──
            String courseTitle = getFirstArchivedCourseTitle();
            System.out.println("Archive has course → unarchiving: " + courseTitle);

            clickFirstCourseDots();
            Thread.sleep(1000);

            clickUnarchive();
            Thread.sleep(1500);

            // Assert archive is now empty
            assertArchiveIsEmpty();
        }
    }
}