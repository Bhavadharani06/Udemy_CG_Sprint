package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class ExplorePage {
	
	WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions act;

    public String expectedURL;
    public String expectedTitle;
    private String selectedCourseTitle;
    String email;
    private String capturedURL;
    private String capturedTitle;
    

    public ExplorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.js = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    
    // Explore Menu
    @FindBy(css = "[aria-label='Explore']")
    WebElement exploreMenu;

    // Course List
    @FindBy(css = "//a[href*='/course/']")
    List<WebElement> courses;
    
    // The title inside the course card on the search/category page
    private String courseTitleLocator = ".//h3 | .//div[contains(@class,'course-title')]";
    
    @FindBy(xpath = "//select[@name='sort' and @form='filter-form']")
    WebElement sortDropdown;

    @FindBy(xpath = "//label[contains(.,'4.0')]")
    WebElement rating4;

    @FindBy(xpath = "//span[.='Video Duration']/ancestor::button")
    WebElement videoDuration;

    @FindBy(xpath = "//span[.='Topic']/ancestor::button")
    WebElement topic;

    @FindBy(xpath = "//label[contains(., 'Painting')]")
    WebElement painting;

    @FindBy(xpath = "//span[.='Subcategory']/ancestor::button")
    WebElement subCategoryFilter;

    @FindBy(xpath = "//label[contains(., 'Arts & Crafts')]")
    WebElement artsCrafts;

    @FindBy(xpath = "//span[.='Level']/ancestor::button")
    WebElement level;

    @FindBy(xpath = "//input[@value='all']/parent::*")
    WebElement allLevels;

    @FindBy(xpath = "//button[.//span[contains(text(),'Language')]]")
    WebElement language;

    @FindBy(xpath = "//input[@value='en']/following-sibling::*")
    WebElement english;

    @FindBy(xpath = "//span[.='Price']/ancestor::button")
    WebElement price;

    @FindBy(xpath = "//input[@value='price-free']")
    WebElement free;
    
    @FindBy(xpath = "//span[.='Trending']")
    private WebElement trendingTab;
    
    @FindBy(xpath = "//a[contains(@href,'/course/')]")
    private List<WebElement> courseLinks;

    @FindBy(xpath = "//h1")
    private WebElement courseTitle;

    @FindBy(id = "subscriptionTrialOfferButton")
    private WebElement subscribeBtn;
    
    @FindBy(name = "email")
     WebElement emailField;
    
 // LOCATORS
    @FindBy(xpath = "//h2[contains(text(),'Trending')]")
    private WebElement trendingHeader;

    @FindBy(xpath = "//button[contains(@data-purpose, 'subscribe')] | //button[contains(.,'Try Personal Plan')]")
    private WebElement subscribeButton;
    By addToCartBtn = By.xpath("//button[@data-testid='add-to-cart-button']");
    By goToCartBtn = By.xpath("//span[text()='Go to cart']");
    By proceedCheckoutBtn = By.xpath("//span[text()='Proceed to Checkout']/..");
    By emailID = By.xpath("//input[@type='email']");


    // ========================= COMMON =========================

    
    public void waitForCloudflareToDisappear() {
        try {
            // Wait for the Udemy logo or a main nav item to appear instead of title text
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Udemy']")));
        } catch (Exception e) {
            System.out.println("Cloudflare check timed out or logo not found.");
        }
    }

    public void clickExplore() {
        WebElement explore = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Explore']"))
        );
        explore.click();
    }
    
    public void selectCategory(String category) {

        By categoryLocator = By.xpath(
            "//*[self::span or self::div or self::button]" +
            "[normalize-space()='" + category + "']"
        );

        WebElement cat = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryLocator));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", cat);

        wait.until(ExpectedConditions.elementToBeClickable(cat));

        try {
            cat.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", cat);
        }
    }
      

    public void selectSubCategory(String subCategory) {

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // STEP 1: wait for ANY submenu panel to load
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[text()='" + subCategory + "']")
        ));

        // STEP 2: now search inside visible UI//span[text()='Web Development']
        By subCategoryLocator = By.xpath("//*[span[text()='" + subCategory + "']]");

        WebElement sub = wait.until(
            ExpectedConditions.elementToBeClickable(subCategoryLocator)
        );

        // STEP 3: scroll into view
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", sub);

        // STEP 4: JS click (IMPORTANT for Udemy)
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", sub);
    }
//    public String clickFirstCourse() {
//
//        // ✅ Scroll to trigger lazy loading
//    	js.executeScript("window.scrollTo({top:1300, behavior: 'smooth'});");
//
//        By courses = By.cssSelector("a[href*='/course/']");
//
//        // ✅ Wait until courses are visible
//        List<WebElement> courseList = wait.until(
//            ExpectedConditions.visibilityOfAllElementsLocatedBy(courses)
//        );
//
//        if (courseList.isEmpty()) {
//            throw new RuntimeException("❌ No courses found!");
//        }
//
//        // ✅ Get first visible course
//        WebElement firstCourse = courseList.stream()
//            .filter(WebElement::isDisplayed)
//            .findFirst()
//            .orElseThrow(() -> new RuntimeException("No visible course found"));
//
//        String url = firstCourse.getAttribute("href");
//
//        String title = firstCourse.getText().trim();
//
//        System.out.println("Captured Title: " + title);
//        System.out.println("Captured URL: " + url);
//
//        // ✅ Click using JS (Udemy needs this)
//        js.executeScript("arguments[0].click();", firstCourse);
//
//        return url + "||" + title;
//    }
    
    public String clickFirstCourse() {

        By courses = By.cssSelector("a[href*='/course/']");

        // ✅ Wait for elements to be present (NOT visible)
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(courses));

        // ✅ Scroll to trigger lazy loading
        js.executeScript("window.scrollBy(0, 1000)");

        List<WebElement> courseList = driver.findElements(courses);

        if (courseList.isEmpty()) {
            throw new RuntimeException("❌ No courses found after filters!");
        }

        WebElement firstCourse = null;

        // ✅ Find first valid visible course safely
        for (int i = 0; i < courseList.size(); i++) {
            try {
                WebElement el = courseList.get(i);
                if (el.isDisplayed()) {
                    firstCourse = el;
                    break;
                }
            } catch (StaleElementReferenceException e) {
                // retry by refetching
                courseList = driver.findElements(courses);
            }
        }

        if (firstCourse == null) {
            throw new RuntimeException("❌ No visible course found!");
        }

        String url = firstCourse.getAttribute("href");
        String title = firstCourse.getText().trim();

        System.out.println("Captured Title: " + title);
        System.out.println("Captured URL: " + url);

        // ✅ JS click (more stable)
        js.executeScript("arguments[0].click();", firstCourse);

        return url + "||" + title;
    }
    
    public String getCapturedTitle() {
        return capturedTitle;
    }

    public String getCapturedURL() {
        return capturedURL;
    }
    
    public boolean isCoursesDisplayed() {
        List<WebElement> courses = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//a[contains(@href,'/course/')]")
            )
        );
        return courses.size() > 0;
    }


    public void switchTab() {
        String parent = driver.getWindowHandle();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
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
    
    public void scrollToFilters() {

        WebElement filter = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Topic']")
            )
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", filter);
    }
    
    public void selectSortOption() {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        act.sendKeys(Keys.ARROW_DOWN)
               .sendKeys(Keys.ARROW_DOWN)
               .sendKeys(Keys.ENTER)
               .perform();
    }

    public void selectRating() {
        wait.until(ExpectedConditions.elementToBeClickable(rating4)).click();
    }

    public void selectVideoDuration() {
        // Click "Show more" first
        WebElement showMore = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@aria-label,'Show more')]")
            )
        );
        js.executeScript("arguments[0].click();", showMore);

        // Now click Video Duration
        wait.until(ExpectedConditions.elementToBeClickable(videoDuration));
        js.executeScript("arguments[0].click();", videoDuration);
    }
    public void selectTopic() {
        wait.until(ExpectedConditions.elementToBeClickable(topic));
        js.executeScript("arguments[0].click();", topic);

        wait.until(ExpectedConditions.elementToBeClickable(painting));
        js.executeScript("arguments[0].click();", painting);
    }

    public void selectSubCategoryFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(subCategoryFilter));
        js.executeScript("arguments[0].click();", subCategoryFilter);

        wait.until(ExpectedConditions.elementToBeClickable(artsCrafts));
        js.executeScript("arguments[0].click();", artsCrafts);
    }

    public void selectLevel() {
        wait.until(ExpectedConditions.elementToBeClickable(level));
        js.executeScript("arguments[0].click();", level);

        wait.until(ExpectedConditions.elementToBeClickable(allLevels));
        js.executeScript("arguments[0].click();", allLevels);
    }
    public void selectLanguage() {

    	WebElement language = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Language']/parent::button")));
		js.executeScript("arguments[0].click();", language);
		js.executeScript("window.scrollBy({top: 100, behavior: 'smooth'})");
		WebElement english = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='en']/following-sibling::*[1] ")));

        // Close popup
//        By closeBtn = By.xpath("//button[@aria-label='Close']");
//
//        WebElement close = wait.until(
//            ExpectedConditions.elementToBeClickable(closeBtn)
//        );
//
//        close.click();
    }
//    public void selectLanguage() {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        // 1. Scroll to filters area first (VERY IMPORTANT on Udemy)
//        js.executeScript("window.scrollBy(0, 600);");
//
//        // 2. Click Language filter (more stable XPath)
//        By languageLocator = By.xpath("//button[.//span[contains(text(),'Language')]]");
//
//        WebElement languageBtn = wait.until(
//            ExpectedConditions.presenceOfElementLocated(languageLocator)
//        );
//
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", languageBtn);
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(languageBtn)).click();
//        } catch (Exception e) {
//            js.executeScript("arguments[0].click();", languageBtn);
//        }
//
//        // 3. Wait for dropdown content to load
//        By englishLocator = By.xpath("//label[contains(.,'English')]");
//
//        WebElement english = wait.until(
//            ExpectedConditions.presenceOfElementLocated(englishLocator)
//        );
//
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", english);
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(english)).click();
//        } catch (Exception e) {
//            js.executeScript("arguments[0].click();", english);
//        }
//    }

    public void selectPrice() {

        js.executeScript("window.scrollBy(0, 900)");

        By priceBtnLocator = By.xpath("//button[.//span[text()='Price']]");

        WebElement priceBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(priceBtnLocator)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", priceBtn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(priceBtn)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", priceBtn);
        }

        // Select Free option
        By freeLocator = By.xpath("//label[contains(.,'Free')]");

        WebElement freeOption = wait.until(
            ExpectedConditions.presenceOfElementLocated(freeLocator)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", freeOption);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(freeOption)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", freeOption);
        }
    }
    public boolean verifyCoursesDisplayed() {

        try {
            // Wait for either courses OR "no results"
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'/course/')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'No results')]"))
            ));

            List<WebElement> courses = driver.findElements(By.xpath("//a[contains(@href,'/course/')]"));

            if (courses.size() > 0) {
                System.out.println("Courses found: " + courses.size());
                return true;
            } else {
                System.out.println("No courses found after filters");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
    
    public String clickRandomCourse() {

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector("div[data-purpose='loading-spinner']")
        ));

        // Wait for courses
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//a[contains(@href,'/course/')]")
        ));

        // Scroll to TOP (force)
        js.executeScript("window.scrollTo(0,0)");

        try { Thread.sleep(2000); } catch (Exception e) {}

        // Fetch fresh elements
        List<WebElement> courseList = driver.findElements(
            By.xpath("//a[contains(@href,'/course/')]")
        );

        if(courseList.size() == 0){
            throw new RuntimeException("No courses found after filtering");
        }

        WebElement firstCourse = courseList.get(0);

        // Scroll properly
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstCourse);

        String url = firstCourse.getAttribute("href");

        // Wait clickable
        wait.until(ExpectedConditions.elementToBeClickable(firstCourse));

        try {
            firstCourse.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", firstCourse);
        }

        return url;
    }
    
    public void clickTrending() {
        // Scroll to the Trending section if it's further down the page
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", trendingHeader);
//        wait.until(ExpectedConditions.visibilityOf(trendingHeader));
        WebElement trending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Trending']"))
        );
        js.executeScript("arguments[0].click();", trending);
    }
    
    public boolean isOnCoursePage() {
    	wait.until(ExpectedConditions.visibilityOf(courseTitle));
        return driver.getCurrentUrl().contains("course");
    }
    
    public boolean isTitleMatching() {
    	String actualTitle = courseTitle.getText().trim();

        System.out.println("Expected: " + expectedTitle);
        System.out.println("Actual: " + actualTitle);

        return actualTitle.equalsIgnoreCase(expectedTitle);
    }
    
    public boolean validateCourseTitle() {

        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))
        );

        String actualTitle = title.getText().trim();

        System.out.println("Expected: " + expectedTitle);
        System.out.println("Actual: " + actualTitle);

        return actualTitle.equalsIgnoreCase(expectedTitle);
    }
    public void clickSubscribe() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOf(subscribeBtn));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isOnAuthPage() {
        wait.until(ExpectedConditions.urlContains("/join/"));
        String url = driver.getCurrentUrl();

        return url.contains("/join/") ||  emailField.isDisplayed() ;
    }
    
    public void applyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'4.0')]"))).click();
    }
    
     //Hover on FIRST visible course
    public void hoverOnFirstCourse() {
        List<WebElement> courses = wait.until(
            ExpectedConditions.visibilityOfAllElements(courseLinks)
        );

        WebElement firstCourse = courses.get(0);

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstCourse);

        act.moveToElement(firstCourse).pause(Duration.ofSeconds(3)).perform(); // 🔥 increase wait
    }
       
       //Click Add to Cart (popup)
    public void clickAddToCart() {
    	By addToCart = By.xpath("//button[contains(@data-testid,'add-to-cart')]");
    	By goToCart = By.xpath("//span[text()='Go to cart']/ancestor::button");

        try {
            // Wait for ANY cart button
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(addToCart),
                ExpectedConditions.presenceOfElementLocated(goToCart)
            ));

            // Check which is present
            if (driver.findElements(addToCart).size() > 0) {
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
                js.executeScript("arguments[0].click();", addBtn);
                System.out.println("✅ Clicked Add to Cart");

            } else if (driver.findElements(goToCart).size() > 0) {
                WebElement goBtn = wait.until(ExpectedConditions.elementToBeClickable(goToCart));
                js.executeScript("arguments[0].click();", goBtn);
                System.out.println("✅ Clicked Go to Cart");
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Cart button not found");
        }
    }
      // Click Go to Cart
         public void clickGoToCart() {
        	    By addToCartLocator = By.xpath("//button[@data-testid='add-to-cart-button']");
        	    
        	    // Wait for button and click via JS
        	    WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        	    js.executeScript("arguments[0].click();", cartBtn);
        	    
        	    // CHANGE: Wrap the success check in a try-catch with a SHORTER wait
        	    // This prevents the 60-second hang if the redirect happens instantly
        	    try {
        	        WebDriverWait quickWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        	        quickWait.until(ExpectedConditions.or(
        	            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Go to cart']")),
        	            ExpectedConditions.urlContains("/cart/")
        	        ));
        	    } catch (Exception e) {
        	        System.out.println("Proceeding... either redirected or popup didn't show.");
        	    }
        	}
 	    // Click Proceed to Checkout
//         public void clickProceedToCheckout() {
//        	    // Wait for the URL to definitely be the cart page
//        	    wait.until(ExpectedConditions.urlContains("/cart/"));
//
//        	    // Robust locator for the Proceed button
//        	    By proceedBtn = By.xpath("//button[@type='button' and @class='ud-btn ud-btn-large ud-btn-brand ud-btn-text-md checkout-button_checkout-button__I0Cdn'] | //span[text()='Proceed to Checkout']/parent::button");
//
//        	    // Wait until the button is not just present, but clickable
//        	    WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
//
//        	    // Scroll and Click via JS to bypass any "floating" footer overlays
//        	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", checkout);
//        	    js.executeScript("arguments[0].click();", checkout);
//        	}
         
         public void clickProceedToCheckout() {
        	    // 1. URL check
        	    wait.until(ExpectedConditions.urlContains("/cart/"));

        	    By proceedBtn = By.xpath("//button[@data-purpose='proceed-to-checkout'] | //button[contains(.,'Proceed to Checkout')]");

        	    try {
        	        // 2. IMPORTANT: Wait for calculations to finish
        	        Thread.sleep(2500); 

        	        // 3. Find and scroll
        	        WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(proceedBtn));
        	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", checkout);
        	        
        	        // 4. Force click via JS
        	        js.executeScript("arguments[0].click();", checkout);
        	        System.out.println("Checkout button clicked successfully.");
        	    } catch (Exception e) {
        	        // Final fallback: Direct DOM selector
        	        js.executeScript("document.querySelector(\"button[data-purpose='proceed-to-checkout']\").click();");
        	    }
        	}
 
 	 // Change the method signature to accept a String parameter
 	    public void enterEmail(String emailValue) {
 	        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
 	        emailInput.clear();
 	        emailInput.sendKeys(emailValue); // emailValue will no longer be null
 	    }
 	    
// 	    public boolean isOnLoginPage() {
// 
// 	        return wait.until(ExpectedConditions.visibilityOfElementLocated(
// 	                By.xpath("//input[@type='email']")
// 	        )).isDisplayed();
// 	    }
 	    
 	   By checkoutContainer = By.xpath("//div[contains(@data-purpose,'checkout')]");

 	  public boolean isOnPaymentPage() {
 	      try {
 	          return wait.until(
 	              ExpectedConditions.visibilityOfElementLocated(checkoutContainer)
 	          ).isDisplayed();
 	      } catch (Exception e) {
 	          return false;
 	      }
 	  }

}
