package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArchivePage {

	// ================= ELEMENTS =================

	@FindBy(xpath = "//a[contains(@href,'archived')]")
	private WebElement archivedTab;

	@FindBy(xpath = "//a[contains(text(),'Go to the All Courses')]")
	private WebElement goToAllCoursesLink;

	@FindBy(xpath = "(//button[contains(@id,'dropdown-trigger')])[1]")
	private WebElement firstCourseThreeDots;

	@FindBy(xpath = "//button[contains(.,'Archive')]")
	private WebElement archiveBtn;

	@FindBy(xpath = "//button[contains(.,'Unarchive')]")
	private WebElement unarchiveBtn;

	// Empty state text (from your screenshot)
	@FindBy(xpath = "//h3[contains(text(),'Focus on only the courses')]")
	private WebElement emptyArchiveText;

	// ================= GETTERS =================

	public WebElement getArchivedTab() {
		return archivedTab;
	}

	public WebElement getGoToAllCoursesLink() {
		return goToAllCoursesLink;
	}

	public WebElement getFirstCourseThreeDots() {
		return firstCourseThreeDots;
	}

	public WebElement getArchiveBtn() {
		return archiveBtn;
	}

	public WebElement getUnarchiveBtn() {
		return unarchiveBtn;
	}

	// ================= BASIC ACTION METHODS =================

	public void clickArchivedTab() {
		archivedTab.click();
	}

	public void clickGoToAllCourses() {
		goToAllCoursesLink.click();
	}

	public void clickThreeDots() {
		firstCourseThreeDots.click();
	}

	public void clickArchive() {
		archiveBtn.click();
	}

	public void clickUnarchive() {
		unarchiveBtn.click();
	}

	// ================= VALIDATION =================

	public boolean isArchiveEmpty() {
		try {
			return emptyArchiveText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// ================= BUSINESS LOGIC =================

	// Case 1: Archive when empty
	public void archiveCourseFromAllCourses() {
		clickGoToAllCourses();
		clickThreeDots();
		clickArchive();
	}

	// Case 2: Unarchive when present
	public void unarchiveCourse() {
		clickThreeDots();
		clickUnarchive();
	}

	// MAIN FLOW
	public void handleArchiveFlow() {

		clickArchivedTab();

		if (isArchiveEmpty()) {

			System.out.println("Archive is empty → Archiving a course");

			archiveCourseFromAllCourses();

		} else {

			System.out.println("Archive has courses → Unarchiving");

			unarchiveCourse();
		}
	}
}