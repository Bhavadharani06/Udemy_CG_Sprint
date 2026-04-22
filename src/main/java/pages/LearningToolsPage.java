package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.AllFunctionality;

public class LearningToolsPage {

    WebDriver driver;

    public LearningToolsPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= ELEMENT ACTIONS =================

    public void clickLearningToolsTab() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//a[contains(@href,'learning-tools')]"), 20).click();
    }

    public void clickCreateReminder() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//button[@data-purpose='create-reminder-button']"), 20).click();
    }

    public void selectCourse(String courseName) {
        AllFunctionality.waitClickable(driver,
                By.xpath("//label[contains(.,'" + courseName + "')]"), 20).click();
    }

    public void clickNext() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//button[@data-purpose='next-button']"), 20).click();
    }

    public void selectFrequency(String freq) {
        AllFunctionality.waitClickable(driver,
                By.xpath("//label[normalize-space()='" + freq + "']"), 20).click();
    }

    public void enterTime(String time) {
        WebElement timeField = AllFunctionality.waitVisible(driver, By.xpath("//input[@type='time']"), 20);

        timeField.clear();
        timeField.sendKeys(time);
    }

    public void enterDate(String date) {
        WebElement dateField = AllFunctionality.waitVisible(driver, By.xpath("//input[@placeholder='MM/DD/YYYY']"), 20);

        dateField.clear();
        dateField.sendKeys(date);
    }

    public void clickDone() {
        AllFunctionality.waitClickable(driver,
                By.xpath("//button//span[normalize-space()='Done']"), 20).click();
    }

    // ================= BUSINESS LOGIC =================

    public void createReminderFlow(String courseName, String time, String date, String freq) {

        clickLearningToolsTab();

        clickCreateReminder();

        selectCourse(courseName);

        clickNext();

        selectFrequency(freq);

        enterTime(time);

        enterDate(date);

        clickDone();

        System.out.println("Reminder created successfully");
    }
}