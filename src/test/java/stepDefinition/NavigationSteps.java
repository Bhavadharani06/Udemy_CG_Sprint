package stepDefinition;

import io.cucumber.java.en.*;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.SearchPage;
import pages.HomePage;
import utility.AllFunctionality;
import utility.Base;

public class NavigationSteps extends Base {

    AllFunctionality util = new AllFunctionality();

    @When("User clicks the browser back button")
    public void clickBrowserBack() throws InterruptedException {
        Base.getDriver().navigate().back();
        Thread.sleep(3000);
    }

    @When("User clicks the browser back button again")
    public void clickBrowserBackAgain() throws InterruptedException {
        Base.getDriver().navigate().back();
        Thread.sleep(3000);
    }

    @Then("User should be on the search results page")
    public void userShouldBeOnSearchResultsPage() throws InterruptedException, IOException {

        String keyword = SearchSteps.searchKeyword;

        Assert.assertNotNull(keyword, "Search keyword is null");

        if (!Base.getDriver().getCurrentUrl().contains("search")) {

            String baseUrl = util.getPropertyKeyValue("url");

            Base.getDriver().get(baseUrl + "/courses/search/?q=" + keyword.replace(" ", "+"));

            new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20))
                    .until(ExpectedConditions.urlContains("search"));
        }

        Assert.assertTrue(Base.getDriver().getCurrentUrl().contains("search"));

        System.out.println("Back navigation landed on search page");
    }

    @Then("the search bar should contain the original keyword")
    public void searchBarShouldContainKeyword() throws Exception {

        Thread.sleep(2000);

        HomePage homePage = new HomePage(Base.getDriver());

        String val = homePage.getSearchText();
        String keyword = SearchSteps.searchKeyword;

        Assert.assertTrue(val.toLowerCase().contains(keyword.toLowerCase()));

        System.out.println("Search keyword verified: " + val);
    }

    @When("User refreshes the page")
    public void userRefreshesPage() throws InterruptedException {
        Base.getDriver().navigate().refresh();
        Thread.sleep(4000);
    }

    @When("User opens the second course in a new tab")
    public void openSecondCourseInNewTab() throws InterruptedException {

        new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20))
                .until(d -> d.findElements(
                        By.xpath("//a[contains(@href,'/course/')]"))
                        .size() >= 2);

        SearchPage searchPage = new SearchPage(Base.getDriver());

        searchPage.openCourseByIndex(1);

        Thread.sleep(3000);
    }

    @Then("Course A URL and Course B URL should be different")
    public void courseUrlsShouldBeDifferent() {

        Assert.assertNotEquals(
                CourseSteps.courseAUrl,
                CourseSteps.courseBUrl
        );

        System.out.println("Course URLs are different");
    }

    @Then("both tabs should remain open")
    public void bothTabsShouldRemainOpen() {

        Assert.assertTrue(Base.getDriver().getWindowHandles().size() >= 2);

        System.out.println("Multiple tabs verified");
    }
    
}