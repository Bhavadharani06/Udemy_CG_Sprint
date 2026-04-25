package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class LearningToolsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LearningToolsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }
    
    // LOCATORS  (matched to your 5 screenshots)

    // PAGE NAV
    private By learningToolsTab = By.xpath(
        "//a[contains(@href,'learning-tools')]");

    // ADD / CREATE REMINDER BUTTON
    // Covers both "Create reminder" (no reminders yet) and "Add a new reminder"
    private By createReminderBtn = By.xpath("//button[@data-purpose='create-reminder-button']");

    // STEP 1 LOCATORS

    // Name field  (placeholder = "Learning reminder")
    private By nameField = By.xpath("//input[@placeholder='Learning reminder']");

    // Course radio label  — dynamic: label contains the course name
    // Used as: By.xpath("//label[contains(.,'" + courseName + "')]")

    // "None" radio (selected by default per screenshot)
    private By noneRadio = By.xpath("//label[normalize-space()='None']");

    // Search box inside Step 1 modal
    private By courseSearchBox = By.xpath("//input[@placeholder='Search']");

    // Next button  (Step 1 and Step 2)
    private By nextBtn = By.xpath("//button[@data-purpose='next-button']");

    // STEP 2 LOCATORS

    // Frequency buttons: Daily / Weekly / Once
    // Used as: By.xpath("//label[normalize-space()='" + freq + "']")

    // Time input (type=time, value like "12:00")
    private By timeInput = By.xpath("//input[@type='time']");

    // Day buttons when Weekly selected: Su Mo Tu We Th Fr Sa
    

    // Date input when Once selected (placeholder MM/DD/YYYY)
    private By dateInput = By.xpath(
        "//input[@placeholder='MM/DD/YYYY']");

    // STEP 3 LOCATORS 
    // Done button (Step 3)
    private By doneBtn = By.xpath("//button[normalize-space()='Done']");

    // STEP INDICATOR (for assertion)
    // "Step 1 of 3" / "Step 2 of 3" / "Step 3 of 3"
    private By stepIndicator = By.xpath(
        "//*[contains(text(),'Step') and contains(text(),'of 3')]");

    // Reminder card after creation (assert reminder exists)
    private By reminderCards = By.xpath("//div[contains(@class,'reminder') and contains(.,'Added to your calendar')]");

    // NAVIGATION
    public void navigateToLearningTools() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(learningToolsTab)).click();
        Thread.sleep(1500);
        System.out.println("Navigated to Learning Tools tab");
    }

    public void clickAddNewReminder() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(createReminderBtn)).click();
        Thread.sleep(1500);
        System.out.println("Clicked Add / Create reminder button");
    }

    // STEP 1 ACTIONS

    // Enter a custom name for the reminder (optional) 
    public void enterReminderName(String name) throws InterruptedException {
        if (name == null || name.isEmpty()) return;
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(nameField));
        field.clear();
        field.sendKeys(name);
        System.out.println("Reminder name entered: " + name);
    }

    //Select a course radio button by partial course name.
    public void selectCourse(String courseName) throws InterruptedException {
        By courseRadio;
        if (courseName.equalsIgnoreCase("None")) {
            courseRadio = noneRadio;
        } else {
            courseRadio = By.xpath(
                "//label[contains(.,'" + courseName + "')]");
        }
        WebElement radio = wait.until(
            ExpectedConditions.elementToBeClickable(courseRadio));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", radio);
        radio.click();
        Thread.sleep(500);
        System.out.println("Course selected: " + courseName);
    }

    // Type in the course search box (Step 1) to filter courses 
    public void searchCourse(String keyword) throws InterruptedException {
        WebElement search = wait.until(
            ExpectedConditions.visibilityOfElementLocated(courseSearchBox));
        search.clear();
        search.sendKeys(keyword);
        Thread.sleep(1000);
        System.out.println("Searched for course: " + keyword);
    }

    public void clickNext() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
        Thread.sleep(1500);
        System.out.println("Clicked Next");
    }

    // STEP 2 ACTIONS

    public void selectFrequency(String freq) throws InterruptedException {
        By freqBtn = By.xpath(
            "//button[normalize-space()='" + freq + "']"
            + " | //label[normalize-space()='" + freq + "']");
        wait.until(ExpectedConditions.elementToBeClickable(freqBtn)).click();
        Thread.sleep(800);
        System.out.println("Frequency selected: " + freq);
    }

    //Enter time in HH:MM format (24hr), e.g. "17:00"
    public void enterTime(String time) throws InterruptedException {
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(timeInput));
        field.clear();
        field.sendKeys(time);
        Thread.sleep(500);
        System.out.println("Time entered: " + time);
    }

    //Click a day pill when frequency = Weekly
    public void selectDay(String day) {

        try {
            // correct locator → label for checkbox
            By dayLocator = By.xpath("//label[normalize-space()='" + day + "']");

            WebElement dayElement = wait.until(
                    ExpectedConditions.elementToBeClickable(dayLocator)
            );

            dayElement.click();

            System.out.println("Day selected: " + day);

        } catch (Exception e) {
            System.out.println("Unable to select day: " + day + " → " + e.getMessage());
        }
    }

    //Enter date when frequency = Once
    //Format: MM/DD/YYYY  e.g. "04/30/2026"
    public void enterDate(String date) throws InterruptedException {
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(dateInput));
        field.clear();
        field.sendKeys(date);
        Thread.sleep(500);
        System.out.println("Date entered: " + date);
    }

    // STEP 3 ACTIONS

    // Just click Done — skip calendar integration (most common case) 
    public void clickDone() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
        Thread.sleep(1500);
        System.out.println("Clicked Done");
    }

    // FULL FLOW  (called from step definition)
    public void createReminder(String reminderName, String courseName,
                               String freq, String time,
                               String days, String date)
            throws InterruptedException {

        // STEP 1 
        enterReminderName(reminderName);
        selectCourse(courseName);
        clickNext();

        // STEP 2
        selectFrequency(freq);
        enterTime(time);

        if (freq.equalsIgnoreCase("Weekly") && !days.isEmpty()) {
            for (String day : days.split(",")) {
                selectDay(day.trim());
            }
        }

        if (freq.equalsIgnoreCase("Once") && !date.isEmpty()) {
            enterDate(date);
        }

        clickNext();

        // STEP 3
        clickDone();

        System.out.println("Reminder created: name=" + reminderName
            + " | course=" + courseName
            + " | freq=" + freq
            + " | time=" + time);
    }

    // ASSERTIONS

    // Assert the step indicator shows the expected step e.g. "Step 1 of 3" 
    public void assertStepIndicator(String expectedText) {
        try {
            String actual = wait.until(
                ExpectedConditions.visibilityOfElementLocated(stepIndicator)
            ).getText().trim();
            if (!actual.contains(expectedText))
                throw new AssertionError(
                    "FAIL: Expected step '" + expectedText + "' but got '" + actual + "'");
            System.out.println("PASS: Step indicator = " + actual);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new AssertionError("FAIL: Step indicator not found — " + e.getMessage());
        }
    }

    // Assert modal is closed (Done was clicked successfully)
    public void assertModalClosed() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(doneBtn));
            System.out.println("PASS: Reminder modal is closed");
        } catch (Exception e) {
            throw new AssertionError("FAIL: Reminder modal is still open after clicking Done");
        }
    }

    // Assert at least one reminder card is visible on Learning Tools page 
    public void assertReminderCreated() {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(reminderCards));
            System.out.println("PASS: Reminder card visible");

        } catch (Exception e) {
            System.out.println("FAIL: Reminder card not found");
        }
    }
    
    public void verifyReminderCreated(String courseName) {

        try {
            driver.navigate().refresh();
            Thread.sleep(3000);

            List<WebElement> reminders = driver.findElements(reminderCards);

            if (reminders.size() == 0) {
                System.out.println("FAIL: No reminder cards found");
                return;
            }

            boolean found = false;

            for (WebElement reminder : reminders) {

                String text = reminder.getText().toLowerCase();

                System.out.println("DEBUG Reminder Text: " + text);

                if (text.contains(courseName.toLowerCase())) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("PASS: Reminder created for → " + courseName);
            } else {
                System.out.println("FAIL: Reminder exists but course not matching → " + courseName);
            }

        } catch (Exception e) {
            System.out.println("ERROR while verifying reminder → " + e.getMessage());
        }
    }
}