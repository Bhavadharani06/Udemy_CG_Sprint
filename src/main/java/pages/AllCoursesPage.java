package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class AllCoursesPage {

	// LOCATORS

	@FindBy(xpath = "//a[contains(.,'All courses')]")
	private WebElement allCoursesTab;

	// GETTERS

	public WebElement getAllCoursesTab() {
		return allCoursesTab;
	}

	// ACTION METHODS

	public void clickAllCourses(WebDriver driver) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(allCoursesTab))
				.click();
	}

	// SCROLL METHOD

	public void scrollToLoadCourses(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// NO COURSE MESSAGE

	public boolean isNoCourseMessageDisplayed(WebDriver driver) {
		try {
			WebElement msg = driver.findElement(By.xpath("//h3[contains(text(),'Start learning')]"));
			return msg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// GET ALL COURSES

	public List<WebElement> getAllCourses(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@data-purpose='course-title-url']")));

		return driver.findElements(By.xpath("//h3[@data-purpose='course-title-url']"));
	}

	// CHECK COURSE

	public boolean isCoursePresent(WebDriver driver, String courseName) {

		try {
			// Step 1: Check if no course message is displayed
			if (isNoCourseMessageDisplayed(driver)) {
				return false;
			}

			// Step 2: Scroll to load courses
			scrollToLoadCourses(driver);

			// Step 3: Get all courses
			List<WebElement> courses = getAllCourses(driver);

			// Step 4: Match course name
			for (WebElement course : courses) {
				if (course.getText().toLowerCase().contains(courseName.toLowerCase())) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			return false;
		}
	}

	// ASSERTION

	public void verifyCoursePresent(WebDriver driver, String courseName) {

		if (isNoCourseMessageDisplayed(driver)) {
			throw new AssertionError("No courses available on page");
		}

		boolean result = isCoursePresent(driver, courseName);

		if (result) {
			System.out.println("Course FOUND: " + courseName);
		} else {
			throw new AssertionError("Course NOT FOUND: " + courseName);
		}
	}
}