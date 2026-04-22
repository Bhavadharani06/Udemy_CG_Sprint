package utility;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.*;

public class Pages {

    public static HomePage homePage;
    public static SearchResultsPage searchResultsPage;
    public static CartPage cartPage;
    public static CommonstepsPage common;
    public static Explore1Page page1;
    public static Explore2Page page2;
    public static Explore3Page page3;
    public static Explore4Page page4;
    public static Explore5Page page5;
    public static ExplorePage explorepage;
    
   // public static SignupPage signupPage;

	public static void initPages(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        common = PageFactory.initElements(driver, CommonstepsPage.class);
        page1 = PageFactory.initElements(driver, Explore1Page.class);
        page2 = PageFactory.initElements(driver, Explore2Page.class);
        page3 = PageFactory.initElements(driver, Explore3Page.class);
        page4 = PageFactory.initElements(driver, Explore4Page.class);
        page5 = PageFactory.initElements(driver, Explore5Page.class);
        explorepage = PageFactory.initElements(driver, ExplorePage.class);
    }
}