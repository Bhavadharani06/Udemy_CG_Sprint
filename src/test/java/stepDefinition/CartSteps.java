package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;
import pages.CoursePage;
import pages.HomePage;
import pages.SearchPage;
import utility.AllFunctionality;
import utility.Base;

public class CartSteps extends Base {

    AllFunctionality util = new AllFunctionality();

    CartPage cartPage;
    CoursePage coursePage;

    private int cartSizeBeforeRetry = 0;

    @Then("User should be on the cart page")
    public void userShouldBeOnCartPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"));
    }

    @Then("the cart should contain the added course")
    public void cartShouldContainAddedCourse() {
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartCount() >= 1);
    }

    @Then("the cart should contain at least one course")
    public void cartShouldContainAtLeastOne() {
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartCount() >= 1);
    }

    @Then("the cart should contain exactly {int} course")
    public void cartShouldContainExactly(int expected) {
        cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartCount(), expected);
    }

    @Then("there should be no duplicate courses in the cart")
    public void noDuplicatesInCart() {
        cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getUniqueItemCount(), cartPage.getCartCount());
    }

    @Then("the cart item count should not increase")
    public void cartCountShouldNotIncrease() {
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartCount() <= cartSizeBeforeRetry);
    }

    @Then("the cart count should still be {int}")
    public void cartCountShouldStillBe(int expected) {
        cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartCount(), expected);
    }

    @When("User navigates to the cart page")
    public void userNavigatesToCartPage() throws Exception {
        driver.get(util.getPropertyKeyValue("cartUrl"));
        Thread.sleep(3000);
    }

    @When("User clears the cart")
    public void userClearsCart() throws Exception {
    		driver.get(Hooks.prop.getProperty("cartUrl"));
    		Thread.sleep(3000);
        cartPage = new CartPage(driver);
        cartPage.clearCart();
    }

    @Given("User has a course in the cart")
    public void userHasCourseInCart() throws Exception {
        driver.get(util.getPropertyKeyValue("cartUrl"));
        Thread.sleep(3000);

        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartCount() >= 1);
    }

    // ✅ UNIQUE STEP (no duplicate conflict)
    @When("User clicks remove button")
    public void userClicksRemoveButton() throws InterruptedException {

        cartPage = new CartPage(driver);

        if (!cartPage.removeButtons.isEmpty()) {
            cartPage.removeButtons.get(0).click();
            Thread.sleep(2000);
        }
    }

    @Then("the course should be removed from the cart")
    public void courseShouldBeRemovedFromCart() {
        boolean empty = driver.getPageSource().toLowerCase().contains("your cart is empty");
        Assert.assertTrue(empty);
    }

    @Given("User adds the course again to the cart")
    public void userAddsTheCourseAgain() throws Exception {

        driver.get(util.getPropertyKeyValue("url"));
        Thread.sleep(3000);

        util.loadExcelFile(
                System.getProperty("user.dir") + "/src/test/resources/testdata/TestData.xlsx",
                "Sheet1"   // ✅ FIXED
        );

        String keyword = util.getDataFromSingleCell(1, 1);

        HomePage home = new HomePage(driver);
        home.searchCourse(keyword);

        Thread.sleep(5000);

        SearchPage search = new SearchPage(driver);
        search.openFirstCourse();

        coursePage = new CoursePage(driver);
        coursePage.clickAddToCart();

        Thread.sleep(2000);

        driver.get(util.getPropertyKeyValue("cartUrl"));
        Thread.sleep(3000);
    }

    @Then("the cart should be empty")
    public void cartShouldBeEmpty() {
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("your cart is empty"));
    }

    @When("User navigates back and tries adding the same course again")
    public void navigateBackAndTryAddingAgain() throws Exception {

        cartPage = new CartPage(driver);
        cartSizeBeforeRetry = cartPage.getCartCount();

        driver.navigate().back();
        Thread.sleep(3000);

        coursePage = new CoursePage(driver);

        try {
            coursePage.clickAddAllToCart();
        } catch (Exception e) {
            // ignore
        }

        driver.get(util.getPropertyKeyValue("cartUrl"));
        Thread.sleep(3000);
    }
}