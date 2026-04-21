package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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

    // ✅ Private constructor (per thread)
    private Pages(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        home = PageFactory.initElements(driver, InstructorHomePage.class);
        search = PageFactory.initElements(driver, InstructorSearchResultsPage.class);
        course = PageFactory.initElements(driver, InstructorCoursePage.class);
        social = PageFactory.initElements(driver, InstructorSocialLinkPage.class);
     
        
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