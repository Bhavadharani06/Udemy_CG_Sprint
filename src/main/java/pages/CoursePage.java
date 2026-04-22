package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage {

    WebDriver driver;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Go to cart')]")
    public WebElement goToCartBtn;

    @FindBy(tagName = "h1")
    public WebElement courseTitle;

    @FindBy(xpath = "//div[contains(@class,'instructor')]//a")
    public WebElement instructor;

    // ✅ THIS IS YOUR WORKING VERSION
    public void clickAddAllToCart() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // scroll a bit (not full bottom)
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(3000);

        try {
            WebElement addAll = driver.findElement(
                    By.xpath("//button[contains(.,'Add all to cart')]")
            );

            js.executeScript("arguments[0].click();", addAll);
            System.out.println("✔ Add all to cart clicked");

        } catch (Exception e) {

            System.out.println("⚠ Add all not available, using Add to cart");

            // 🔥 STRONGER LOCATOR (VERY IMPORTANT)
            WebElement addBtn = driver.findElement(
                    By.xpath("//button[contains(.,'Add to cart') or contains(.,'Enroll now') or contains(.,'Buy now')]")
            );

            js.executeScript("arguments[0].click();", addBtn);
            System.out.println("✔ Add to cart clicked");
        }

        Thread.sleep(2000);
    }

    public void clickGoToCart() throws InterruptedException {

        goToCartBtn.click();
        Thread.sleep(2000);

        System.out.println("✔ Navigated to cart page");
    }

    public String getCourseTitle() {
        return courseTitle.getText();
    }

    public String getInstructorName() {

        String text = instructor.getText().trim();

        if (text.contains(",")) {
            text = text.split(",")[0];
        }

        return text.trim();
    }

    public void clickInstructor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", instructor);
    }
    
    public void clickAddToCart() {

        try {
            List<WebElement> buttons = driver.findElements(
                    By.xpath("//button[contains(.,'Add to cart')]")
            );

            if (buttons.size() > 0) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttons.get(0));
                System.out.println("Add to cart clicked");
            } else {
                System.out.println("Add to cart not found (maybe already added)");
            }

        } catch (Exception e) {
            System.out.println("Skipping Add to cart: " + e.getMessage());
        }
    }
}