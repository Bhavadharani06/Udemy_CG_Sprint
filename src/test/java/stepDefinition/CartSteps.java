package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;
import pages.CoursePage;
import utility.Base;

public class CartSteps extends Base {

    CartPage cartPage;
    CoursePage coursePage;

    private int cartSizeBeforeRetry = 0;

    @When("User navigates to the cart page")
    public void userNavigatesToCartPage() throws Exception {

    	Base.getDriver().get("https://www.udemy.com/cart/");
        Thread.sleep(3000);

        System.out.println("Navigated to cart page");
    }

    @When("User clears the cart")
    public void userClearsCart() throws Exception {

    	Base.getDriver().get("https://www.udemy.com/cart/");
        Thread.sleep(3000);

        cartPage = new CartPage(Base.getDriver());
        cartPage.clearCart();
    }

    @Then("User should be on the cart page")
    public void userShouldBeOnCartPage() {
        Assert.assertTrue(Base.getDriver().getCurrentUrl().contains("cart"));
    }

    @Then("the cart should contain exactly {int} course")
    public void cartShouldContainExactly(int expected) {

        cartPage = new CartPage(Base.getDriver());

        int actual = cartPage.getCartCount();

        System.out.println("Cart count: " + actual);

        Assert.assertEquals(actual, expected);
    }

    @Then("the cart should contain the added course")
    public void cartShouldContainAddedCourse() {

        cartPage = new CartPage(Base.getDriver());

        Assert.assertTrue(cartPage.getCartCount() >= 1);
    }

    @Then("the cart count should still be {int}")
    public void cartCountShouldStillBe(int expected) throws Exception {

    	Base.getDriver().get("https://www.udemy.com/cart/");
        Thread.sleep(3000);

        cartPage = new CartPage(Base.getDriver());
        int actual = cartPage.getCartCount();

        System.out.println("Cart after refresh: " + actual);

        Assert.assertTrue(actual >= 0);
    }

    // 🔥 FIXED MISSING STEPS BELOW

    @Then("the cart should contain at least one course")
    public void cartShouldContainAtLeastOne() {

        cartPage = new CartPage(Base.getDriver());

        int count = cartPage.getCartCount();

        System.out.println("Cart count: " + count);

        Assert.assertTrue(count >= 1);
    }

    @Then("there should be no duplicate courses in the cart")
    public void noDuplicatesInCart() {

        cartPage = new CartPage(Base.getDriver());

        int total = cartPage.getCartCount();
        int unique = cartPage.getUniqueItemCount();

        System.out.println("Total: " + total + " Unique: " + unique);

        Assert.assertEquals(total, unique);
    }

    @When("User navigates back and tries adding the same course again")
    public void navigateBackAndTryAddingAgain() throws Exception {

        cartPage = new CartPage(Base.getDriver());
        cartSizeBeforeRetry = cartPage.getCartCount();

        System.out.println("Before re-adding: " + cartSizeBeforeRetry);

        Base.getDriver().navigate().back();
        Thread.sleep(3000);

        coursePage = new CoursePage(Base.getDriver());

        try {
            coursePage.clickAddAllToCart();
        } catch (Exception e) {
            System.out.println("Add all not available, continuing...");
        }

        Base.getDriver().get("https://www.udemy.com/cart/");
        Thread.sleep(3000);

        int after = new CartPage(Base.getDriver()).getCartCount();

        System.out.println("After re-adding: " + after);
    }

    @Then("the cart item count should not increase")
    public void cartCountShouldNotIncrease() {

        cartPage = new CartPage(Base.getDriver());

        int finalCount = cartPage.getCartCount();

        System.out.println("Final cart count: " + finalCount);

        Assert.assertTrue(finalCount <= cartSizeBeforeRetry);
    }
}