# Udemy Test Automation Framework
**Capgemini BDD Selenium Sprint Project – April 2026**

---

## Project Overview

This is a collaborative BDD automation framework built by a team of 5 members for the **Udemy** e-learning platform. Each team member independently developed and tested their assigned module in a separate Git branch. All branches were reviewed and merged into the `main` branch for the final submission.

The framework automates real user journeys across Udemy including course discovery, cart management, search, instructor navigation, explore, and the complete My Learning dashboard — running in **parallel** using TestNG.

---

## Team and Branch Structure

| Member | Branch | Module |
|---|---|---|
| Bhavadharani | mylearning | My Learning — All Courses, Wishlist, Archive, My Lists, Learning Tools |
| Swaathihaa | cart-management | Cart — Add, Remove, Save for Later, No Duplicates, Persistence |
| Nisha | course-management | Course and Instructor — Search, Course Detail, Instructor Profile, Social Links |
| Kiruthikaa | explore | Explore — Category Navigation, Filters, Subscription, Checkout |
| Varshini | search-management | Search and Filter — Excel-driven Search, Free Filter, Negative Testing |

All branches merged into main as the final integrated project.

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 21 | Programming language |
| Selenium WebDriver 4.x | Browser automation |
| Cucumber BDD | Feature files and step definitions |
| TestNG 7.x | Test runner and parallel execution |
| Apache POI | Excel test data reading |
| ExtentReports | HTML test execution reports |
| Maven | Build and dependency management |
| ChromeDriver 147 | Browser driver |

---

## Project Structure

```
Udemy_CG_Sprint/
│
├── src/main/java/
│   ├── pages/
│   │   ├── AllCoursesPage.java             # All Courses tab — play video, navigate
│   │   ├── ArchivePage.java                # Archive and Unarchive courses
│   │   ├── CartPage.java                   # Cart — add, remove, save for later
│   │   ├── CommonstepsPage.java            # Shared common page actions
│   │   ├── CoursePage.java                 # Course detail page
│   │   ├── Explore1Page.java               # Explore — Category URL validation
│   │   ├── Explore2Page.java               # Explore — Title navigation
│   │   ├── Explore3Page.java               # Explore — Filters
│   │   ├── Explore4Page.java               # Explore — Subscription
│   │   ├── Explore5Page.java               # Explore — Cart checkout
│   │   ├── ExplorePage.java                # Main Explore page
│   │   ├── HomePage.java                   # Home page and top navigation
│   │   ├── InstructorCoursePage.java       # Instructor course listing
│   │   ├── InstructorHomePage.java         # Instructor home page
│   │   ├── InstructorPage.java             # Instructor detail page
│   │   ├── InstructorSearchResultsPage.java# Instructor search results
│   │   ├── InstructorSocialLinkPage.java   # LinkedIn and YouTube validation
│   │   ├── LearningToolsPage.java          # Learning reminders 3-step modal
│   │   ├── MyListPage.java                 # My Lists — create and verify
│   │   ├── SearchPage.java                 # Search bar actions
│   │   ├── SearchResultsPage.java          # Search results page
│   │   ├── SignUpPage.java                 # User signup flow
│   │   └── WishlistPage.java               # Wishlist — add and verify
│   │
│   └── utility/
│       ├── AllFunctionality.java           # Reusable Selenium methods and Excel reader
│       ├── Base.java                       # ThreadLocal WebDriver manager
│       ├── ExtentReportManager.java        # Extent HTML report initialization
│       ├── HandleCookies.java              # Cookie save and load for login
│       ├── Pages.java                      # ThreadLocal Page Factory
│       ├── ScreenshotUtil.java             # Screenshot on failure
│       └── TestNGListener.java             # TestNG and Extent report listener
│
├── src/main/resources/
│   └── CommonData/
│       └── config.properties               # URL, browser, credentials
│
├── src/test/java/
│   ├── features/
│   │   ├── all_courses.feature             # My Learning — All Courses
│   │   ├── archive.feature                 # My Learning — Archive
│   │   ├── wishlist.feature                # My Learning — Wishlist
│   │   ├── my_list.feature                 # My Learning — My Lists
│   │   ├── learning_tools.feature          # My Learning — Learning Reminders
│   │   ├── search_cart_flow.feature        # Cart — Add, Save, Remove
│   │   ├── add_all_no_duplicates.feature   # Cart — No Duplicates
│   │   ├── cart_persistence.feature        # Cart — Persistence after navigation
│   │   ├── BUG_002invalidCartUrl.feature   # Bug — Invalid cart URL 404
│   │   ├── Instructor.feature              # Instructor flow
│   │   ├── InstructorFlow.feature          # Instructor flow parallel
│   │   ├── course_navigation_tabs.feature  # Course URL comparison
│   │   ├── navigation_instructor_back.feature  # Back navigation
│   │   ├── Explore.feature                 # Explore — full suite
│   │   ├── explore1.feature to explore5.feature  # Explore individual
│   │   ├── searchmanagement.feature        # Search and Filter Excel driven
│   │   └── signup.feature                  # User Signup
│   │
│   ├── stepDefinition/
│   │   ├── Hooks.java                      # Before and After — browser, cookies, reports
│   │   ├── AllCoursesSteps.java
│   │   ├── ArchiveSteps.java
│   │   ├── WishlistSteps.java
│   │   ├── MyListSteps.java
│   │   ├── LearningToolsSteps.java
│   │   ├── CartSteps.java
│   │   ├── InstructorSteps.java
│   │   ├── InstructorFlowSteps.java
│   │   ├── NavigationSteps.java
│   │   ├── Explore.java to Explore5.java
│   │   ├── SearchManagement_Filter.java
│   │   ├── SearchSteps.java
│   │   ├── SignUpSteps.java
│   │   └── Commonsteps.java
│   │
│   └── testRunner/
│       └── RunnerIO.java                   # TestNG + Cucumber runner with parallel support
│
└── src/test/resources/
    └── testdata/
        └── TestData.xlsx                   # Excel test data with multiple sheets
```

---

## Scenarios Covered

### My Learning — @my_learnings (Bhavadharani)

| Feature | Scenarios | Key Coverage |
|---|---|---|
| All Courses | 4 | Video playback, DataTable, Excel verification, Negative check |
| Wishlist | 1 | Dynamic add if empty, verify |
| Archive | 1 | Archive if empty, Unarchive if present |
| My Lists | 1 | Create list via 3 dots, verify |
| Learning Tools | 3 | Scenario Outline — Daily, Weekly, Once reminders |

### Cart Management — @cart (Swaathihaa)

| Feature | Scenarios | Key Coverage |
|---|---|---|
| Add to Cart | 1 | Search and add course |
| Save for Later | 1 | Move course to saved |
| Remove from Cart | 1 | Remove course |
| No Duplicates | 1 | Add all and verify no duplicates |
| Cart Persistence | 1 | Scenario Outline — count after navigation and refresh |

### Course and Instructor — @Instructor_parallel (Nisha)

| Feature | Scenarios | Key Coverage |
|---|---|---|
| Search Results | 1 | Search and verify listing |
| Add to Cart | 1 | Open course and add to cart |
| Instructor Profile | 1 | Navigate to instructor page |
| LinkedIn | 1 | Verify LinkedIn opens |
| YouTube | 1 | Verify YouTube opens |
| Invalid Search | 1 | Negative — no results |

### Explore — @udemy_all (Kiruthikaa)

| Feature | Scenarios | Key Coverage |
|---|---|---|
| Category URL | 1 | Scenario Outline — multiple categories |
| Title Navigation | 1 | Scenario Outline — course titles |
| Filters | 1 | Apply all filters |
| Subscription | 1 | Scenario Outline — subscription button |
| Cart Checkout | 1 | Add and proceed to checkout |

### Search and Filter — @searchfilter (Varshini)

| Feature | Scenarios | Key Coverage |
|---|---|---|
| Add from Excel | 1 | Scenario Outline — Excel-driven courses |
| Free Course | 1 | Free filter and enroll |
| Clear Filters | 1 | Clear and add to cart |
| Invalid Search | 1 | Negative — invalid keyword from Excel |
| Miscellaneous | 1 | General search verification |
| Invalid Cart URL | 1 | Negative — invalid URL navigates to cart instead of 404 |

---

## Authentication

- Login uses **cookie persistence** stored in `cookies1.data`
- First run: browser opens, user logs in manually, cookies save automatically
- All subsequent runs: cookies load instantly with no manual login
- Scenarios tagged `@signup` skip cookie loading entirely

---

## Test Data

| Sheet | Columns | Used In |
|---|---|---|
| CourseData | TC_ID, Course Name | All Courses Excel verification |
| Sheet1 | TC_ID, Course, InvalidCourse | Search management |
| Explore | Category, Course, URL | Explore scenarios |

---

## Configuration

```properties
url=https://www.udemy.com
cartUrl=https://www.udemy.com/cart/
myLearningURL=https://www.udemy.com/home/my-courses/
invalidCartUrl=https://www.udemy.com/cart/random123hdgfd
browser=chrome
```

---

## How to Run

```bash
# My Learning module
mvn test -Dcucumber.filter.tags="@my_learnings"

# Cart module
mvn test -Dcucumber.filter.tags="@cart"

# Search module
mvn test -Dcucumber.filter.tags="@searchfilter"

# Explore module
mvn test -Dcucumber.filter.tags="@udemy_all"

# Instructor module
mvn test -Dcucumber.filter.tags="@Instructor_parallel"

# Full suite
mvn test
```

---

## Reports

| Report | Location |
|---|---|
| Extent HTML | target/ExtentReport/ |
| Cucumber HTML | target/cucumber-report.html |
| JSON | target/cucumber.json |
| JUnit XML | target/cucumber.xml |

Screenshots are captured on failure and attached to both Extent and Cucumber reports automatically.

---

## Framework Design

| Feature | Detail |
|---|---|
| Page Object Model | Each page has a dedicated class with locators and action methods |
| ThreadLocal WebDriver | Base.java ensures parallel scenarios never share the same driver |
| ThreadLocal Pages | Pages.java gives each thread its own page instances |
| Cookie Login | HandleCookies.java saves and reloads session for fast login |
| Hooks | Before launches browser and loads cookies, After takes screenshot and quits |
| Excel Integration | AllFunctionality.java reads xlsx files using Apache POI |
| Extent Reports | TestNGListener generates rich HTML reports with failure screenshots |
| Parallel Execution | DataProvider parallel true in RunnerIO runs scenarios concurrently |

---

## Git Workflow

```
main (final submission)
├── mylearning        → Bhavadharani
├── cart-management   → Swaathihaa
├── course-management → Nisha
├── explore           → Kiruthikaa
└── search-management → Varshini
```

**Capgemini BDD Selenium Sprint — April 2026**
