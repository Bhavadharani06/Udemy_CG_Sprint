package utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.*;

public class Pages {

    public static HomePage homePage;
    public static SearchResultsPage searchResultsPage;
    public static CartPage cartPage;
   // public static SignupPage signupPage;

    public static void initPages(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
    }
}