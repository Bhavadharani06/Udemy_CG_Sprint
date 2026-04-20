
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ===== PRIVATE ELEMENTS =====
    @FindBy(xpath = "//strong[.='All filters']")
    private WebElement allFilters;

    @FindBy(xpath = "//button[@data-purpose='close-btn']")
    private WebElement closeFiltersBtn;

    @FindBy(xpath = "//input[@name='certification_topics']")
    private WebElement certificationPrep;

    @FindBy(xpath = "//span[.='4.5 out of 5 & up' and @class='ud-sr-only']")
    private WebElement ratingFilter;

    @FindBy(xpath = "//div[contains(@class,'ud-form-group')][.//label[text()='Language']]//label[contains(.,'English')]")
    private WebElement englishLanguage;

    @FindBy(xpath = "//span[.='Clear all filters']")
    private WebElement clearFilters;

    @FindBy(xpath = "//label[text()='Price']/following::label[contains(., 'Free')][1]")
    private WebElement freeCourse;

    @FindBy(xpath = "(//button[.='Add to cart'])[1]")
    private WebElement addToCartBtn;

//    @FindBy(xpath = "//span[.='Enroll now']")
//    private WebElement enrollNowBtn;
    private By enrollNowBtn = By.xpath("//span[.='Enroll now']");

    @FindBy(css = "h1[data-purpose='safely-set-inner-html:search:no-results-for-query']")
    private WebElement noResultsMessage;

    // ===== ACTION METHODS =====

    private void openFilters() throws InterruptedException {
        // Wait for page to settle
        Thread.sleep(4000);

        wait.until(ExpectedConditions.visibilityOf(allFilters)).click();
    }

    private void closeFilters() {
        wait.until(ExpectedConditions.visibilityOf(closeFiltersBtn)).click();
    }

    public void applyMainFilters() throws InterruptedException {
        openFilters();

        wait.until(ExpectedConditions.visibilityOf(certificationPrep));
        js.executeScript("arguments[0].click();", certificationPrep);

        wait.until(ExpectedConditions.visibilityOf(ratingFilter));
        js.executeScript("arguments[0].click();", ratingFilter);

        wait.until(ExpectedConditions.visibilityOf(englishLanguage));
        js.executeScript("arguments[0].click();", englishLanguage);

        closeFilters();
    }

    public void applyFreeFilter() throws InterruptedException {
        openFilters();

        wait.until(ExpectedConditions.visibilityOf(freeCourse));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", freeCourse);
        wait.until(ExpectedConditions.elementToBeClickable(freeCourse));
        freeCourse.click();

        closeFilters();
    }

    public void clearAllFilters() throws InterruptedException {
        openFilters();

        wait.until(ExpectedConditions.visibilityOf(clearFilters));
        js.executeScript("arguments[0].click();", clearFilters);

        closeFilters();
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
        js.executeScript("arguments[0].click();", addToCartBtn);
    }

//    public void clickEnrollNow() throws InterruptedException {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        WebElement enrollBtn = wait.until(
//            ExpectedConditions.elementToBeClickable(By.xpath("(//a[.//span[text()='Enroll now']])[1]"))
//        );
//
//        // Scroll to button
//        ((JavascriptExecutor) driver).executeScript(
//            "arguments[0].scrollIntoView({block:'center'});", enrollBtn
//        );
//
//        Thread.sleep(1500); // human delay
//
//        // Move mouse like real user
//        Actions actions = new Actions(driver);
//        actions.moveToElement(enrollBtn).perform();
//
//        Thread.sleep(1000);
//
//        // Click
//        enrollBtn.click();
//    }

    public boolean isNoResultsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(noResultsMessage)).isDisplayed();
    }

//    public boolean isEnrollSuccessful() {
//        return wait.until(ExpectedConditions.visibilityOf(enrollNowBtn)).isDisplayed();
//    }
////    public void clickGoToSignup() {
//        // Using your specific locator from the snippet
//        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
//            By.xpath("//a[contains(@href,'/join/signup')]")));
//        signUpLink.click();
//    }
    public boolean isEnrollNowVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(enrollNowBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}