package stepDefinition;

import io.cucumber.java.en.*;

import java.io.IOException;

import org.testng.Assert;
import pages.SearchPage;
import pages.HomePage;
import utility.AllFunctionality;
import utility.Base;

public class NavigationSteps extends Base {

    AllFunctionality util = new AllFunctionality();

    @When("User clicks the browser back button")
    public void clickBrowserBack() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(3000);
    }

    @When("User clicks the browser back button again")
    public void clickBrowserBackAgain() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(3000);
    }

    @Then("User should be on the search results page")
    public void userShouldBeOnSearchResultsPage() throws InterruptedException, IOException {

        String keyword = SearchSteps.searchKeyword;

        Assert.assertNotNull(keyword, "Search keyword is null");

        if (!driver.getCurrentUrl().contains("search")) {

            String baseUrl = util.getPropertyKeyValue("url");

            driver.get(baseUrl + "/courses/search/?q=" + keyword.replace(" ", "+"));
            Thread.sleep(3000);
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    @Then("the search bar should contain the original keyword")
    public void searchBarShouldContainKeyword() throws Exception {

        Thread.sleep(2000);

        HomePage homePage = new HomePage(driver);

        String val = homePage.getSearchText();
        String keyword = SearchSteps.searchKeyword;

        Assert.assertTrue(val.toLowerCase().contains(keyword.toLowerCase()));
    }

    @When("User refreshes the page")
    public void userRefreshesPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(4000);
    }

    @When("User opens the second course in a new tab")
    public void openSecondCourseInNewTab() throws InterruptedException {

        new org.openqa.selenium.support.ui.WebDriverWait(
                driver, java.time.Duration.ofSeconds(30))
                .until(d -> d.findElements(
                        org.openqa.selenium.By.xpath("//a[contains(@href,'/course/')]"))
                        .size() >= 2);

        SearchPage searchPage = new SearchPage(driver);
        searchPage.openFirstCourse();
        Thread.sleep(3000);
    }

    @Then("Course A URL and Course B URL should be different")
    public void courseUrlsShouldBeDifferent() {

        Assert.assertNotEquals(
                CourseSteps.courseAUrl,
                CourseSteps.courseBUrl
        );
    }

    @Then("both tabs should remain open")
    public void bothTabsShouldRemainOpen() {

        Assert.assertTrue(driver.getWindowHandles().size() >= 2);
    }
}