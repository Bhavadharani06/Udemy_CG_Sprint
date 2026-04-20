package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.AllFunctionality;

public class CoursePage {

    WebDriver driver;
    AllFunctionality util = new AllFunctionality();

    public CoursePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Add to cart') or contains(.,'Add all to cart')]")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//button[contains(.,'Add all to cart')]")
    public WebElement addAllToCartBtn;

    @FindBy(xpath = "//button[contains(.,'Go to cart')]")
    public WebElement goToCartBtn;

    @FindBy(tagName = "h1")
    public WebElement courseTitle;

    @FindBy(xpath = "//a[contains(@href,'/user/') or contains(@href,'/instructor/') or contains(@href,'#instructor')]")
    public WebElement instructor;

    public void clickAddToCart() throws InterruptedException {

        util.scrollIntoView(driver, addToCartBtn);
        Thread.sleep(1000);

        try {
            addToCartBtn.click();
        } catch (Exception e) {
            util.clickJS(driver, addToCartBtn);
        }
    }

    public void clickAddAllToCart() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

       
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        util.scrollIntoView(driver, addAllToCartBtn);
        Thread.sleep(1000);

        util.clickJS(driver, addAllToCartBtn);
    }

    public void clickGoToCart() {
        goToCartBtn.click();
    }

    public String getCourseTitle() {
        return courseTitle.getText();
    }

    public String getInstructorName() {
        return instructor.getText();
    }

    public void clickInstructor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", instructor);
    }
}