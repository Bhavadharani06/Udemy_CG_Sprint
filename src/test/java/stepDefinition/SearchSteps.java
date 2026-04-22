package stepDefinition;

import io.cucumber.java.en.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.HomePage;
import pages.SearchPage;
import utility.AllFunctionality;
import utility.Base;

public class SearchSteps extends Base {

    AllFunctionality util = new AllFunctionality();

    HomePage homePage;
    SearchPage searchPage;

    public static String searchKeyword;
    public static String capturedCourseATitle;

    @Given("User is on the Udemy homepage")
    public void userIsOnHomepage() {

        homePage = new HomePage(Base.getDriver());

        Assert.assertFalse(
                Base.getDriver().getCurrentUrl().contains("challenge"),
                "Captcha not cleared"
        );

        System.out.println("On homepage");
    }

    @When("User searches for the course using test data")
    public void userSearchesUsingTestData() throws Exception {

        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/TestData.xlsx";

        util.loadExcelFile(path, "Sheet1");

        searchKeyword = util.getDataFromSingleCell(1, 1);

        String baseUrl = util.getPropertyKeyValue("url");
        Base.getDriver().get(baseUrl);

        Thread.sleep(3000);

        homePage = new HomePage(Base.getDriver());
        homePage.searchCourse(searchKeyword);

        new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("search"));

        System.out.println("Searched: " + searchKeyword);
        System.out.println("Current URL: " + Base.getDriver().getCurrentUrl());
    }

    @Then("the search results page should display relevant courses")
    public void searchResultsShouldDisplayCourses() {

        List<WebElement> courses = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(30))
                .until(d -> {
                    List<WebElement> list = d.findElements(
                            By.xpath("//a[contains(@href,'/course/') and contains(@class,'ud-link-neutral')]")
                    );
                    return list.size() >= 2 ? list : null;
                });

        System.out.println("Courses found: " + courses.size());

        Assert.assertTrue(courses.size() > 0, "No courses found");
    }

    @Then("the search results page should display at least 2 courses")
    public void searchResultsAtLeastTwoCourses() {

        List<WebElement> courses = Base.getDriver().findElements(
                By.xpath("//a[contains(@href,'/course/')]")
        );

        System.out.println("Courses found: " + courses.size());

        Assert.assertTrue(courses.size() >= 2, "Less than 2 courses found");
    }

    @When("User clicks the first course link and switches to new tab if opened")
    public void clickFirstCourseSwitchTab() throws InterruptedException {

        searchPage = new SearchPage(Base.getDriver());
        searchPage.openFirstCourse();
    }

    @When("User clicks the first course link and captures its title")
    public void clickFirstCourseCaptureTitle() throws InterruptedException {

        searchPage = new SearchPage(Base.getDriver());

        capturedCourseATitle = searchPage.getCourseTitle(0);

        searchPage.openFirstCourse();

        System.out.println("Captured Title: " + capturedCourseATitle);
    }

    @When("User clicks the first course link")
    public void clickFirstCourseLink() throws InterruptedException {

        searchPage = new SearchPage(Base.getDriver());
        searchPage.openFirstCourse();
    }
}