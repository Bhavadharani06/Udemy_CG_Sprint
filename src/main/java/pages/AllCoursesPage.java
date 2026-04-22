package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utility.AllFunctionality;

public class AllCoursesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public AllCoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // LOCATORS — verified from your screenshots & DevTools

    // All course title cards on My Learning > All Courses
    private By courseCards = By.xpath("//h3[@data-purpose='course-title-url']");

    // First course card
    private By firstCourseCard = By.xpath("(//h3[@data-purpose='course-title-url'])[1]");

    // "Start learning" — shown when no courses enrolled
    private By noCourseMsg = By.xpath("//h3[contains(text(),'Start learning')]");

    // VIDEO PLAYER

    // Play button  (from your DevTools: data-purpose='video-play-button-initial')
    private By playButton = By.xpath("//button[@data-purpose='video-play-button-initial']");

    // Speed / playback-rate button  (the "1x" button visible in toolbar)
    private By speedButton = By.xpath("//button[@data-purpose='playback-rate-button']");

    // Volume / mute button  (from your DevTools: data-purpose='volume-control-button')
    private By volumeButton = By.xpath("//button[@data-purpose='volume-control-button']");

    // Settings gear  (quality dropdown trigger)
    private By settingsButton = By.xpath("//button[@data-purpose='settings-button']");

    // Back-to-courses link  (from DevTools: data-purpose='mobile-header-bar-back-link'
    //  wrapping an <a> with href to /home/my-courses/)
    private By backArrow = By.xpath(
        "//a[contains(@href,'/home/my-courses/') and @data-purpose='mobile-header-bar-back-link']"
        + " | //a[@class='curriculum-item-view--my-courses-link--jFqDa']");

    // Course title shown inside the player  (from DevTools: class contains 'curriculum-item-view--course-title')
    private By courseTitleInPlayer = By.xpath(
        "//span[contains(@class,'curriculum-item-view--course-title')]");

    // NAVIGATION

    // Direct navigation to My Learning > All Courses 
    public void navigateToAllCourses() {
        driver.get("https://www.udemy.com/home/my-courses/learning/");
        System.out.println("Navigated to All Courses page");
    }

    // COURSE CHECKS
    // True when the "Start learning" (no-courses) message is visible 
    public boolean isNoCourseMessageDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(noCourseMsg)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // All course-title elements currently rendered on the page 
    public List<WebElement> getAllCourses() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(courseCards));
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(1000);
            return driver.findElements(courseCards);
        } catch (Exception e) {
            return List.of();
        }
    }

    // Number of courses visible 
    public int getCourseCount() {
        return getAllCourses().size();
    }
    
    public String getSpeedLabel(int speedIndex) {

        switch (speedIndex) {
            case 1: return "0.5x";
            case 2: return "0.75x";
            case 3: return "1x";
            case 4: return "1.25x";
            case 5: return "1.5x";
            case 6: return "1.75x";
            case 7: return "2x";
            default: return "Invalid";
        }
    }

    // True when any course title matches (case-insensitive) 
    public boolean isCoursePresent(String courseName) {
        for (WebElement c : getAllCourses()) {
            if (c.getText().toLowerCase().contains(courseName.toLowerCase()))
                return true;
        }
        return false;
    }

    // Title text of the first course card 
    public String getFirstCourseTitle() {
        List<WebElement> courses = getAllCourses();
        return courses.isEmpty() ? "" : courses.get(0).getText().trim();
    }

 
    // COURSE ACTIONS
    // Click the first course card to open its learning page 
    public void clickFirstCourse() throws InterruptedException {
        WebElement first = wait.until(
            ExpectedConditions.elementToBeClickable(firstCourseCard));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", first);
        Thread.sleep(800);
        String title = first.getText();
        first.click();
        System.out.println("Clicked first course: " + title);
    }

    // Click a course by its exact (or partial) title 
    public void clickCourseByName(String courseName) throws InterruptedException {
        WebElement course = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//h3[@data-purpose='course-title-url' and contains(.,'" + courseName + "')]")));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", course);
        Thread.sleep(800);
        course.click();
        System.out.println("Clicked course: " + courseName);
    }

    // VIDEO PLAYER ACTIONS

    // Click the initial play button 
    public void playVideo() throws InterruptedException {
        WebElement play = wait.until(
            ExpectedConditions.elementToBeClickable(playButton));
        play.click();
        Thread.sleep(1500);
        System.out.println("Video play clicked");
    }

    // Change playback speed.
    // speedLabel examples: "0.5x", "0.75x", "1x", "1.25x", "1.5x", "1.75x", "2x"
    
    public void changeSpeed(int speedIndex) throws InterruptedException {

        if (speedIndex < 1 || speedIndex > 7) {
            throw new IllegalArgumentException("speedIndex must be 1–7. Got: " + speedIndex);
        }

        // Click speed button (1x button in player)
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(speedButton));
        btn.click();

        Thread.sleep(800);

        // Dynamic XPath using index
        String xpath = "(//ul[@data-purpose='playback-rate-menu']//li)[" + speedIndex + "]";

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );

        String selectedSpeed = option.getText(); // optional (for logging)
        option.click();

        Thread.sleep(500);

        System.out.println("Speed changed to: " + selectedSpeed);
    }
    
    // Toggle mute / unmute 
    public void toggleVolume() throws InterruptedException {
        WebElement vol = wait.until(
            ExpectedConditions.elementToBeClickable(volumeButton));
        vol.click();
        Thread.sleep(500);
        System.out.println("Volume toggled");
    }

    // Change video quality.
    // qualityLabel examples: "Auto", "1080p", "720p", "576p", "432p", "360p"
   
    public void changeQuality(String qualityLabel) throws InterruptedException {
        // Open the settings gear
        WebElement gear = wait.until(
            ExpectedConditions.elementToBeClickable(settingsButton));
        gear.click();
        Thread.sleep(800);

        // Click quality option
        WebElement qualityOption = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space(text())='" + qualityLabel + "']")));
        qualityOption.click();
        Thread.sleep(500);
        System.out.println("Quality changed to: " + qualityLabel);
    }

    
    public String getCourseNameFromPlayer() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(courseTitleInPlayer)
            ).getText().trim();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }

    // Navigate back to All Courses (back-arrow link at top-left of player) 
    public void navigateBackToAllCourses() throws InterruptedException {
        try {
            WebElement back = wait.until(
                ExpectedConditions.elementToBeClickable(backArrow));
            back.click();
            Thread.sleep(2000);
            System.out.println("Navigated back via back-arrow");
        } catch (Exception e) {
        		AllFunctionality allFunctionality = new AllFunctionality();
				allFunctionality.navigateBack(driver);
            Thread.sleep(2000);
            System.out.println("Navigated back via browser");
        }
    }

    
    // ASSERTIONS

    public void assertCoursesPresent() {
        int count = getCourseCount();
        if (count == 0)
            throw new AssertionError("FAIL: No courses found on All Courses page");
        System.out.println("PASS: " + count + " course(s) found");
    }

    public void assertNoCoursesMessage() {
        if (!isNoCourseMessageDisplayed())
            throw new AssertionError("FAIL: 'Start learning' message not displayed");
        System.out.println("PASS: 'Start learning' no-courses message displayed");
    }

    public void assertCoursePresent(String courseName) {
        if (!isCoursePresent(courseName))
            throw new AssertionError("FAIL: Course NOT found: " + courseName);
        System.out.println("PASS: Course found: " + courseName);
    }

    public void assertCourseNotPresent(String courseName) {
        if (isCoursePresent(courseName))
            throw new AssertionError("FAIL: Course SHOULD NOT be present: " + courseName);
        System.out.println("PASS: Course correctly absent: " + courseName);
    }

    public void assertCourseCount(int expected) {
        int actual = getCourseCount();
        if (actual != expected)
            throw new AssertionError(
                "FAIL: Expected " + expected + " courses but found " + actual);
        System.out.println("PASS: Course count = " + actual);
    }

    public void assertCourseNameInPlayer(String expectedName) {
        String actual = getCourseNameFromPlayer();
        if (!actual.toLowerCase().contains(expectedName.toLowerCase()))
            throw new AssertionError(
                "FAIL: Expected '" + expectedName + "' in player, got '" + actual + "'");
        System.out.println("PASS: Player shows: " + actual);
    }
}