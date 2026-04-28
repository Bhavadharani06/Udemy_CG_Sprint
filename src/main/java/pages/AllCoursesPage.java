package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

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

	// LOCATORS

	private By noCourseMsg = By.xpath("//h3[contains(text(),'Start learning')]");
	private By courseCards = By.xpath("//h3[@data-purpose='course-title-url']");
	private By firstCourseCard = By.xpath("(//h3[@data-purpose='course-title-url'])[1]");
	private By playButton = By.xpath("//button[@data-purpose='play-button'] | //button[@aria-label='Play']");
	private By courseTitleInPlayer = By.xpath("//div[contains(@class,'curriculum-header')]//h1 | //h1[@data-purpose='lead-title']");
	private By backArrow = By.xpath("//a[contains(@href,'/my-courses')] | //a[@aria-label='Back']");

	// NAVIGATION

	public void navigateToAllCourses() {
		driver.get("https://www.udemy.com/home/my-courses/learning/");
		System.out.println("Navigated to All Courses page");
	}

	public void navigateBackToAllCourses() throws InterruptedException {
		try {
			driver.findElement(backArrow).click();
		} catch (Exception e) {
			driver.navigate().back(); // fallback
		}
		Thread.sleep(2000);
		System.out.println("Navigated back to All Courses");
	}

	// COURSE CHECKS

	public boolean isNoCourseMessageDisplayed() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(noCourseMsg)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public List<WebElement> getAllCourses() {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(courseCards));
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			return driver.findElements(courseCards);
		} catch (Exception e) {
			return List.of();
		}
	}

	public int getCourseCount() {
		return getAllCourses().size();
	}

	public boolean isCoursePresent(String courseName) {
		for (WebElement course : getAllCourses()) {
			if (course.getText().toLowerCase().contains(courseName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public String getFirstCourseTitle() {
		List<WebElement> courses = getAllCourses();
		if (courses.isEmpty())
			return "";
		return courses.get(0).getText().trim();
	}

	// CLICK FIRST COURSE & PLAY

	public void clickFirstCourse() throws InterruptedException {
		WebElement first = wait.until(ExpectedConditions.elementToBeClickable(firstCourseCard));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", first);
		Thread.sleep(1000);
		first.click();
		System.out.println("Clicked first course");
	}

	public void playVideo() {
		try {
			WebElement play = wait.until(ExpectedConditions.elementToBeClickable(playButton));
			play.click();
			System.out.println("Video playing");
		} catch (Exception e) {
			System.out.println("Play button not found or video already playing");
		}
	}

	public String getCourseNameFromPlayer() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitleInPlayer)).getText().trim();
		} catch (Exception e) {
			return driver.getTitle(); // fallback to page title
		}
	}

}