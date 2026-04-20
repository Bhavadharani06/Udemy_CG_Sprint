package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.AllFunctionality;
import utility.Base;

public class MyListPage {

    WebDriver driver;

    public MyListPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= LOCATORS =================

    // My List tab
    By myListTab = By.xpath("//a[contains(@href,'lists')]");

    // Empty list message
    By emptyListMsg = By.xpath("//h3[contains(text(),'Organize and access')]");

    // All Courses link
    By allCourses = By.xpath("//a[contains(@href,'/home/my-courses') and .='Go to the All Courses tab']");

    // 3 dots menu
    By threeDots = By.xpath("(//button[contains(@id,'dropdown-trigger')])[1]");

    // Create new list button
    By createNewList = By.xpath("(//div[text()='Create New List'])[1]");

    // List name field
    By listNameField = By.xpath("//input[@placeholder='Name your list e.g. HTML skills']");

    // Description field
    By descriptionField = By.xpath("//textarea");

    // Done button
    By doneBtn = By.xpath("//button//span[.='Create']");

    // Created list validation
    public By createdList(String listName) {
        return By.xpath("//h3[contains(.,'" + listName + "')]");
    }

    // ================= ACTION METHODS =================

    public void clickMyListTab() {
        AllFunctionality.waitClickable(driver, myListTab, 20).click();
    }

    public boolean isListEmpty() {
        try {
            return driver.findElement(emptyListMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void goToAllCourses() {
        AllFunctionality.waitClickable(driver, allCourses, 20).click();
    }

    public void clickThreeDots() {
        AllFunctionality.waitClickable(driver, threeDots, 20).click();
    }

    public void clickCreateNewList() {
        AllFunctionality.waitClickable(driver, createNewList, 20).click();
    }

    public void enterListName(String listName) {
        WebElement name = AllFunctionality.waitVisible(driver, listNameField, 20);
        name.clear();
        name.sendKeys(listName);
    }

    public void enterDescription(String desc) {
        WebElement description = AllFunctionality.waitVisible(driver, descriptionField, 20);
        description.clear();
        description.sendKeys(desc);
    }

    public void clickDone() {
        AllFunctionality.waitClickable(driver, doneBtn, 20).click();
    }

    public boolean isListCreated(String listName) {
        try {
            return driver.findElement(createdList(listName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= BUSINESS LOGIC =================

    public void handleMyListFlow(String listName, String desc) {

        clickMyListTab();

        if (isListEmpty()) {

            System.out.println("No list found → Creating new list");

            goToAllCourses();

            clickThreeDots();
            clickCreateNewList();

            enterListName(listName);
            enterDescription(desc);

            clickDone();

            // Go back to My List
            clickMyListTab();

            if (isListCreated(listName)) {
                System.out.println("List created successfully");
            } else {
                System.out.println("List creation failed");
            }

        } else {
            System.out.println("List already exists → No action needed");
        }
    }
}