//package stepDefinition;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.CommonstepsPage;
//import utility.AllFunctionality;
//import utility.Base;
//import utility.Pages;
//
//public class Explore5 extends Base {
//	
//	Commonsteps commonsteps=new Commonsteps();
//	CommonstepsPage common = new CommonstepsPage(driver);
//	Pages pages= new Pages();
//	
//
//    @Given("User opens Udemy website")
//    public void user_opens_udemy_website() {
//       // driver = initializeDriver();   // from Base class
//        //page = new TestCase5(driver);
//
//        driver.get("https://www.udemy.com/");
//    }
//
//    @When("User clicks on Explore and selects {string}")
//    public void user_clicks_on_explore_and_selects(String category) throws InterruptedException {
//        pages.common.clickExplore();
//    	pages.common.selectCategory(category);
//    }
//
//    @When("User hovers on first course and clicks Add to Cart")
//    public void user_hovers_on_first_course_and_clicks_add_to_cart() {
//        pages.page5.hoverOnFirstCourse();              // ✅ open course page
//        pages.page5.clickAddToCart();    // ✅ real add to cart
//    }
//
//    @When("User clicks on Go to Cart")
//    public void user_clicks_on_go_to_cart() {
//        pages.page5.clickGoToCart();
//    }
//
//    @When("User clicks on Proceed to Checkout")
//    public void user_clicks_on_proceed_to_checkout() {
//        pages.page5.clickProceedToCheckout();
//    }
//
//    @When("User enters email from Excel")
//    public void user_enters_email_from_excel() throws Exception {
//        AllFunctionality excel = new AllFunctionality();
//
//        // 1. Load Excel file
//        excel.loadExcelFile("src/test/resources/testdata/TestData.xlsx", "Explore");
//
//        // 2. Read email (Check your row/cell indexes! 0, 2 means Row 1, Column C)
//        String emailFromExcel = excel.getDataFromSingleCell(0, 1);
//        
//        // Debug: Print to console to make sure it's not null
//        System.out.println("Email fetched from Excel: " + emailFromExcel);
//
//        if (emailFromExcel == null) {
//            throw new RuntimeException("The email value fetched from Excel is NULL. Check your Sheet Name and Cell coordinates.");
//        }
//
//        // 3. Pass the fetched email directly to the page method
//        pages.page5.enterEmail(emailFromExcel);
//    }
//    
//    @Then("User should be on login page")
//    public void user_should_be_on_login_page() {
//        Assert.assertTrue(pages.page5.isOnLoginPage(),
//                "User is NOT on login page");
//    }
//
////    @Then("User should be redirected to the checkout page")
////    public void verify_checkout_page() {
////
////        String currentUrl = Hooks.driver.getCurrentUrl();
////
////        Assert.assertTrue(currentUrl.contains("checkout"),
////                "User is NOT on checkout page");
////    }
//    
//    @Then("The application should navigate to checkout page")
//    public void the_application_should_navigate_to_checkout_page() {
//
//        String currentUrl = Hooks.driver.getCurrentUrl();
//
//        Assert.assertTrue(currentUrl.contains("checkout"),
//                "User is NOT on checkout page");
//        
//    }
//}
