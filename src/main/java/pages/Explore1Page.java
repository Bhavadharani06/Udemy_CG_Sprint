package pages;

import java.time.Duration; 
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.AllFunctionality;

public class Explore1Page {
	
	WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    //Actions actions;

    public Explore1Page(WebDriver driver) {
    	 this.driver = driver;
         PageFactory.initElements(driver, this);
         this.js = (JavascriptExecutor) driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
     }

     // Explore Menu
     @FindBy(css = "[aria-label='Explore']")
     WebElement exploreMenu;

     // Course List
     @FindBy(xpath = "//a[contains(@href,'/course/')]")
     List<WebElement> courses;

     //ACTION METHODS

     public void clickExplore() {
         wait.until(ExpectedConditions.elementToBeClickable(exploreMenu)).click();
     }

     // DYNAMIC CATEGORY
     public void selectCategory(String categoryName) throws InterruptedException {

         WebElement category = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//div[text()='" + categoryName + "']")
                 )
         );
        category.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading")));
     }

     // DYNAMIC SUBCATEGORY
     public void selectSubCategory(String subCategoryName) throws InterruptedException {

         WebElement subCategory = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//span[text()='" + subCategoryName + "']")
                 )
         );

         js.executeScript("arguments[0].scrollIntoView(true);", subCategory);

         try {
             subCategory.click();
         } catch (Exception e) {
             js.executeScript("arguments[0].click();", subCategory);
         }
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        		    By.xpath("//a[contains(@href,'/course/')]")
        		));
     }

     public boolean isCoursesDisplayed() {
         wait.until(ExpectedConditions.visibilityOfAllElements(courses));
         return courses.size() > 0;
        
     }

     public String clickFirstCourse() {

          wait.until(ExpectedConditions.visibilityOfAllElements(courses));
          WebElement firstCourse =courses.get(0);

         js.executeScript("arguments[0].scrollIntoView(true);", firstCourse);

         String url = firstCourse.getAttribute("href");

         try {
             firstCourse.click();
         } catch (Exception e) {
             js.executeScript("arguments[0].click();", firstCourse);
         }

         return url;
     }
     
     // Switch tab
     public void switchToNewTabIfPresent() {
         String parent = driver.getWindowHandle();

         for (String window : driver.getWindowHandles()) {
             if (!window.equals(parent)) {
                 driver.switchTo().window(window);
                 break;
             }
         }
     }

     // Get course title
     public String getCourseTitle() {
         WebElement title = wait.until(
             ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))
         );
         return title.getText().trim();
     }
}
