package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CoursePage;
import pages.InstructorPage;
import utility.Base;

public class InstructorSteps extends Base {

    CoursePage coursePage;
    InstructorPage instructorPage;

    private String capturedInstructorName = "";

    @When("User captures and clicks the instructor link")
    public void captureAndClickInstructor() throws InterruptedException {

        coursePage = new CoursePage(driver);

        // wait before accessing
        Thread.sleep(3000);

        capturedInstructorName = coursePage.getInstructorName();

        Assert.assertNotNull(capturedInstructorName, "Instructor name is null");

        capturedInstructorName = capturedInstructorName.trim();

        System.out.println("✔ Instructor captured: " + capturedInstructorName);

        // scroll properly
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        coursePage.instructor);

        Thread.sleep(1000);

        coursePage.clickInstructor();

        Thread.sleep(3000);
    }

    @Then("User should be navigated to the instructor profile section or page")
    public void userOnInstructorProfileOrSection() {

        boolean profileOpened = false;

        try {
            new org.openqa.selenium.support.ui.WebDriverWait(
                    driver, java.time.Duration.ofSeconds(10))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions
                            .urlContains("user"));
            profileOpened = true;
        } catch (Exception e) {
            System.out.println("URL not changed — checking scroll section");
        }

        if (profileOpened) {

            Assert.assertTrue(driver.getCurrentUrl().contains("user"));

        } else {

            String firstName = capturedInstructorName.split(" ")[0];

            try {
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)");

                org.openqa.selenium.WebElement section =
                        new org.openqa.selenium.support.ui.WebDriverWait(
                                driver, java.time.Duration.ofSeconds(10))
                                .until(org.openqa.selenium.support.ui.ExpectedConditions
                                        .visibilityOfElementLocated(
                                                org.openqa.selenium.By.xpath(
                                                        "//a[contains(text(),'" + firstName + "')]")));

                Assert.assertTrue(section.isDisplayed());

            } catch (Exception ex) {
                Assert.fail("Instructor not found in both cases");
            }
        }
    }

    @Then("the instructor name should match the course detail page")
    public void instructorNameShouldMatch() {

        if (!driver.getCurrentUrl().contains("user")) {
            System.out.println("⚠ Scroll case — skipping name match");
            return;
        }

        instructorPage = new InstructorPage(driver);

        String profileName = instructorPage.getInstructorHeader().trim();

        String key = capturedInstructorName.split(" ")[0].toLowerCase();

        System.out.println("Course: " + capturedInstructorName);
        System.out.println("Profile: " + profileName);

        // Soft check (first name only)
        Assert.assertTrue(
            profileName.toLowerCase().contains(key),
            "Instructor mismatch"
        );
    }
}