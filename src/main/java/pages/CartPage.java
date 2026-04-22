package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utility.AllFunctionality;

import java.time.Duration;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private AllFunctionality util = new AllFunctionality();

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        PageFactory.initElements(driver, this);
    }

    // ----------- LOCATORS -----------

    private By closePopupLocator = By.xpath("//button[@data-purpose='close-popup']");

    @FindBy(xpath = "//button[contains(.,'Remove')]")
    public List<WebElement> removeButtons;

    @FindBy(xpath = "//div[contains(@class,'shopping-item_container')]//a[contains(@href,'/course/')]")
    public List<WebElement> cartItems;

    @FindBy(xpath = "//span[text()='Save for Later']/parent::button")
    public WebElement saveForLaterBtn;

    // ----------- METHODS -----------

    public boolean isCourseAdded() {
        WebElement addedToCartText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Added to cart')]")));

        boolean status = addedToCartText.isDisplayed();

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(closePopupLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

        return status;
    }

    public int getCartCount() {
        return cartItems.size();
    }

    public int getUniqueItemCount() {
        Set<String> set = new HashSet<>();

        for (WebElement e : cartItems) {
            set.add(e.getText().trim());
        }

        return set.size();
    }

    public void clickSaveForLater() {
        util.click(driver, saveForLaterBtn);
    }

    public boolean isCartEmpty() {
        return driver.getPageSource().toLowerCase().contains("your cart is empty");
    }
    
    public void clickRemove() {

        try {
            if (removeButtons.size() > 0) {

                WebElement removeBtn = removeButtons.get(0);

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", removeBtn);

                System.out.println("✔ Remove clicked");

                Thread.sleep(2000); // small wait for update

            } else {
                System.out.println("No remove button found");
            }

        } catch (Exception e) {
            System.out.println("Error clicking remove: " + e.getMessage());
        }
    }
    
    public void clearCart() {

        while (true) {

            List<WebElement> buttons = driver.findElements(
                By.xpath("//button[contains(.,'Remove')]")
            );

            if (buttons.size() == 0) break;

            try {
                WebElement btn = buttons.get(0);

                ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", btn);

                Thread.sleep(1500);

            } catch (Exception e) {
                System.out.println("Retry removing...");
            }
        }
    }
}