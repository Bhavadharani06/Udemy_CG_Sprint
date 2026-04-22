package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.*;

public class Pages {

	public static HomePage homePage;
	public static SearchResultsPage searchResultsPage;
	public static CartPage cartPage;

	// User feature pages
	public static SignUpPage signUpPage;
	public static MyListPage myListPage;
	public static WishlistPage wishlistPage;
	public static ArchivePage archivePage;
	public static LearningToolsPage learningToolsPage;
	public static AllCoursesPage allCoursesPage;

	public static void initPages(WebDriver driver) {
		homePage = PageFactory.initElements(driver, HomePage.class);
		searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
		cartPage = PageFactory.initElements(driver, CartPage.class);

		// User pages
        signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        myListPage = PageFactory.initElements(driver, MyListPage.class);
        wishlistPage = PageFactory.initElements(driver, WishlistPage.class);
        archivePage = PageFactory.initElements(driver, ArchivePage.class);
        learningToolsPage = PageFactory.initElements(driver, LearningToolsPage.class);
        allCoursesPage = PageFactory.initElements(driver, AllCoursesPage.class);
        
	}
}