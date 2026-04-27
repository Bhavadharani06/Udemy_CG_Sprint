package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.AllFunctionality;

import java.time.Duration;

public class MyListPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public MyListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // LOCATORS

    // My List tab
    private By myListTab = By.xpath("//a[contains(@href,'lists')]");

    // Empty list message — Image 2: "Organize and access your courses faster!"
    private By emptyListMsg = By.xpath("//h3[contains(text(),'Organize and access')]");

    // "Go to the All Courses tab" link — Image 2
    private By goToAllCoursesLink = By.xpath("//a[contains(@href,'/home/my-courses') and contains(.,'Go to the All Courses tab')]");

    // All Courses tab in nav
    private By allCoursesTab = By.xpath("//a[contains(@href,'learning') and contains(.,'All courses')]");

    // 3 dots button — (//button[contains(@id,'dropdown-trigger')])
    // [1] = first course, [2] = second course
    private By firstThreeDots  = By.xpath("(//button[contains(@id,'dropdown-trigger')])[1]");
    private By secondThreeDots = By.xpath("(//button[contains(@id,'dropdown-trigger')])[2]");

    // Create New List option in dropdown
    private By createNewListBtn1 = By.xpath("(//div[contains(@class,'ud-block-list-item-content') and text()='Create New List'])[1]");
    private By createNewListBtn2 = By.xpath("(//div[contains(@class,'ud-block-list-item-content') and text()='Create New List'])[2]");
    
    // Create new list modal fields
    private By listNameField    = By.xpath("//input[@placeholder='Name your list e.g. HTML skills']");
    private By descriptionField = By.xpath("//textarea[@placeholder='Why are you creating this list? e.g. To start a new business, To get a new job, To become a web developer']");
    private By createBtn        = By.xpath("//button[.//span[text()='Create']]");

    // Verify created list by name in My Lists page
    public By createdListLocator(String listName) {
        return By.xpath("//h3[contains(.,'" + listName + "')]");
    }

    // NAVIGATION

    public void clickMyListTab() {
        AllFunctionality.waitClickable(driver, myListTab, 20).click();
        System.out.println("Clicked My Lists tab");
    }

    public void clickAllCoursesTab() {
        AllFunctionality.waitClickable(driver, allCoursesTab, 20).click();
        System.out.println("Clicked All Courses tab");
    }

    public void clickGoToAllCoursesLink() {
        AllFunctionality.waitClickable(driver, goToAllCoursesLink, 20).click();
        System.out.println("Clicked 'Go to the All Courses tab' link");
    }

    // VALIDATION

    public boolean isListEmpty() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emptyListMsg));
            System.out.println("My Lists is EMPTY");
            return true;
        } catch (Exception e) {
            System.out.println("My Lists has existing lists");
            return false;
        }
    }

    public boolean isListCreated(String listName) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                createdListLocator(listName))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // THREE DOTS ACTIONS

    // Click 3 dots of first course (used when My List is empty → go to All Courses → first course)
    public void clickFirstCourseDots() throws InterruptedException {
        WebElement dots = wait.until(ExpectedConditions.elementToBeClickable(firstThreeDots));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dots);
        Thread.sleep(500);
        dots.click();
        System.out.println("Clicked 3 dots on first course");
    }

    // Click 3 dots of second course (used when My List has courses → All Courses → second course)
    public void clickSecondCourseDots() throws InterruptedException {
        WebElement dots = wait.until(ExpectedConditions.elementToBeClickable(secondThreeDots));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dots);
        Thread.sleep(500);
        dots.click();
        System.out.println("Clicked 3 dots on second course");
    }

    // CREATE LIST MODAL

    public void clickCreateNewList(By createNewListBtn) {
        AllFunctionality.waitClickable(driver, createNewListBtn, 20).click();
        System.out.println("Clicked Create New List");
    }

    public void enterListName(String listName) {
        WebElement field = AllFunctionality.waitClickable(driver, listNameField, 20);
        field.clear();
        field.sendKeys(listName);
        System.out.println("Entered list name: " + listName);
    }

    public void enterDescription(String desc) {
        WebElement field = AllFunctionality.waitClickable(driver, descriptionField, 20);
        field.clear();
        field.sendKeys(desc);
        System.out.println("Entered description: " + desc);
    }

    public void clickCreate() {
        AllFunctionality.waitClickable(driver, createBtn, 20).click();
        System.out.println("Clicked Create button");
    }

    // ASSERTION

    public void assertListCreated(String listName) {
        if (isListCreated(listName))
            System.out.println("PASS: List created and visible — " + listName);
        else
            System.out.println("FAIL: List NOT found — " + listName);
    }

    public void assertListPresent() {
        if (!isListEmpty())
            System.out.println("PASS: My Lists already has lists");
        else
            System.out.println("FAIL: My Lists is empty");
    }

    // MAIN FLOW

    public void handleMyListFlow(String listName, String desc) throws InterruptedException {

        if (isListEmpty()) {

            // ── EMPTY: "Go to the All Courses tab" link visible ──
            System.out.println("My Lists empty → going to All Courses via link");

            // Click "Go to the All Courses tab" link
            clickGoToAllCoursesLink();
            Thread.sleep(2000);

            // Click 3 dots of FIRST course
            clickFirstCourseDots();
            Thread.sleep(1000);

            // Click Create New List
            clickCreateNewList(createNewListBtn1);
            Thread.sleep(1000);

            // Fill modal
            enterListName(listName);
            enterDescription(desc);
            clickCreate();
            Thread.sleep(2000);

            // Navigate back to My Lists
            clickMyListTab();
            Thread.sleep(1500);

            // Verify
            assertListCreated(listName);

        } else {

            // ── HAS COURSES: navigate to All Courses tab manually ──
            System.out.println("My Lists has courses → going to All Courses tab");

            // Click All Courses tab
            clickAllCoursesTab();
            Thread.sleep(2000);

            // Click 3 dots of SECOND course
            clickSecondCourseDots();
            Thread.sleep(1000);

            // Click Create New List
            clickCreateNewList(createNewListBtn2);
            Thread.sleep(1000);

            // Fill modal
            enterListName(listName);
            enterDescription(desc);
            clickCreate();
            Thread.sleep(2000);

            // Navigate back to My Lists
            clickMyListTab();
            Thread.sleep(1500);

            // Verify
            assertListCreated(listName);
        }
    }
}