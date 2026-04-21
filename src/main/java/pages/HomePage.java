package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement searchBox;

    public void searchCourse(String course) throws InterruptedException {

        // 🔥 Step 1: let page load / captcha appear
        Thread.sleep(5000);

        // 🔥 Step 2: manual captcha handling (same as your working test)
        if (!driver.getPageSource().toLowerCase().contains("udemy")) {
            System.out.println("If captcha appears, solve it and press ENTER...");
            new java.util.Scanner(System.in).nextLine();
        }

        // 🔥 Step 3: wait for search box safely
        WebElement search = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );

        // 🔥 Step 4: perform search
        search.click();
        search.clear();
        search.sendKeys(course);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(4000); // stability for demo
    }

    public String getSearchText() {
        return searchBox.getAttribute("value");
    }
}