package utility;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.*;

public class Pages {

	// ✅ Thread-safe instance
	private static final ThreadLocal<Pages> INSTANCE = new ThreadLocal<>();

	// ✅ Page objects (NON-STATIC now)
	public HomePage homePage;
	public SearchResultsPage searchResultsPage;
	public CartPage cartPage;
	public InstructorHomePage home;
	public InstructorSearchResultsPage search;
	public InstructorCoursePage course;
	public InstructorSocialLinkPage social;
	public SignUpPage signUpPage;
	public MyListPage myListPage;
	public WishlistPage wishlistPage;
	public ArchivePage archivePage;
	public LearningToolsPage learningToolsPage;
	public AllCoursesPage allCoursesPage;
	 public  CommonstepsPage common;
	    public  Explore1Page page1;
	    public  Explore2Page page2;
	    public  Explore3Page page3;
	    public  Explore4Page page4;
	    public  Explore5Page page5;
	    public  ExplorePage explorepage;
	// ✅ Private constructor (per thread)
	private Pages(WebDriver driver) {
		homePage = PageFactory.initElements(driver, HomePage.class);
		searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
		cartPage = PageFactory.initElements(driver, CartPage.class);
		home = PageFactory.initElements(driver, InstructorHomePage.class);
		search = PageFactory.initElements(driver, InstructorSearchResultsPage.class);
		course = PageFactory.initElements(driver, InstructorCoursePage.class);
		social = PageFactory.initElements(driver, InstructorSocialLinkPage.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		myListPage = PageFactory.initElements(driver, MyListPage.class);
		wishlistPage = PageFactory.initElements(driver, WishlistPage.class);
		archivePage = PageFactory.initElements(driver, ArchivePage.class);
		learningToolsPage = PageFactory.initElements(driver, LearningToolsPage.class);
		allCoursesPage = PageFactory.initElements(driver, AllCoursesPage.class);
		 common = PageFactory.initElements(driver, CommonstepsPage.class);
	        page1 = PageFactory.initElements(driver, Explore1Page.class);
	        page2 = PageFactory.initElements(driver, Explore2Page.class);
	        page3 = PageFactory.initElements(driver, Explore3Page.class);
	        page4 = PageFactory.initElements(driver, Explore4Page.class);
	        page5 = PageFactory.initElements(driver, Explore5Page.class);
	        explorepage = PageFactory.initElements(driver, ExplorePage.class);
	}

	// ✅ Initialize per thread (call in Hooks)
	public static void initPages(WebDriver driver) {
		INSTANCE.set(new Pages(driver));
	}

	// ✅ Get current thread instance
	public static Pages get() {
		if (INSTANCE.get() == null) {
			throw new RuntimeException("Pages not initialized for this thread!");
		}
		return INSTANCE.get();
	}

	// ✅ Clean up (VERY IMPORTANT)
	public static void remove() {
		INSTANCE.remove();
	}
   

	
}