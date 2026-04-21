package utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.*;

public class Pages {

    public static HomePage homePage;
    public static SearchResultsPage searchResultsPage;
    public static CartPage cartPage;
   // public static SignupPage signupPage;
    public static InstructorHomePage home;
    public static InstructorSearchResultsPage search;
    public static InstructorCoursePage course;
    public static InstructorSocialLinkPage social;

    public static void initPages(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        home = PageFactory.initElements(driver, InstructorHomePage.class);
        search = PageFactory.initElements(driver, InstructorSearchResultsPage.class);
        course = PageFactory.initElements(driver, InstructorCoursePage.class);
        social = PageFactory.initElements(driver, InstructorSocialLinkPage.class);
    }
}