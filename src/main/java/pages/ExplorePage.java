package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class ExplorePage {
	
	WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions act;

    public String expectedURL;
    public String expectedTitle;

    public ExplorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.js = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========================= COMMON =========================

    public void openUrl(String url) {
        driver.get(url);
    }

    public void waitForCloudflare() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Udemy']")));
        } catch (Exception e) {
            System.out.println("Cloudflare wait skipped");
        }
    }

    public void clickExplore() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Explore']"))).click();
    }

    public void selectCategory(String category) {

        WebElement cat = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + category + "']")));

        js.executeScript("arguments[0].click();", cat);

        // IMPORTANT WAIT
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span"))); // wait for submenu to load
    }

//    public void selectSubCategory(String subCategory) {
//        WebElement sub = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//*[span[text()='" + subCategory + "']]")));
//        js.executeScript("arguments[0].scrollIntoView(true);", sub);
//        js.executeScript("arguments[0].click();", sub);
//    }
    
//    public void selectSubCategory(String subCategory) {
//
//        By subLocator = By.xpath("//a[.//span[text()='" + subCategory + "']]");
//
//        WebElement sub = wait.until(ExpectedConditions.visibilityOfElementLocated(subLocator));
//
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", sub);
//
//        wait.until(ExpectedConditions.elementToBeClickable(sub));
//
//        js.executeScript("arguments[0].click();", sub);
//    }

    public void selectSubCategory(String subCategory) {

        // Step 2: Wait for submenu container
        WebElement menuPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'popover')]")));

        // Step 3: Find subcategory INSIDE panel
        WebElement sub = menuPanel.findElement(
                By.xpath(".//span[text()='" + subCategory + "']"));

        // Step 4: Click
        act.moveToElement(sub).pause(Duration.ofSeconds(1)).click().perform();

        System.out.println("Clicked subcategory: " + subCategory);
    }
    // ========================= COURSE =========================

    public boolean isCoursesDisplayed() {
        List<WebElement> courses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")));
        return courses.size() > 0;
    }

    public String clickFirstCourse() {
        List<WebElement> courses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")));

        WebElement first = courses.get(0);

        expectedURL = first.getAttribute("href");
        expectedTitle = first.getText().split("\n")[0];

        js.executeScript("arguments[0].scrollIntoView(true);", first);
        js.executeScript("arguments[0].click();", first);

        return expectedURL;
    }

    public void switchTab() {
        String parent = driver.getWindowHandle();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }

    public String getCourseTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
    }

    // ========================= FILTER =========================

    public void applyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'4.0')]"))).click();
    }

    // ========================= TRENDING =========================

    public void clickTrending() {
        WebElement trending = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[.='Trending']")));
        js.executeScript("arguments[0].click();", trending);
    }

    public void clickSubscribe() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@data-purpose, 'subscribe')]")));
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isOnAuthPage() {
        return driver.getCurrentUrl().contains("join");
    }

    // ========================= CART =========================

    public void hoverFirstCourse() {
        List<WebElement> courses = driver.findElements(By.xpath("//a[contains(@href,'/course/')]"));
        WebElement first = courses.get(0);
        act.moveToElement(first).pause(Duration.ofSeconds(2)).perform();
    }

    public void clickAddToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-testid='add-to-cart-button']")));
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickGoToCart() {
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Go to cart']"))));
    }

    public void clickCheckout() {
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Checkout')]"))));
    }

    public void enterEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        input.clear();
        input.sendKeys(email);
    }

    public boolean isLoginPage() {
        return driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
    }

}
