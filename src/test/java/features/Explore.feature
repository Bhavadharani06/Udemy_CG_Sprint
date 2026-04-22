@udemy_all
Feature: Udemy End-to-End Validation Suite

Background:
  Given User navigates to "https://www.udemy.com/"
  When User clicks on Explore menu

# =========================================================
# SCENARIO 1: Course Navigation Validation (URL check)
# =========================================================

@navigation @url
Scenario Outline: Check and validate for multiple categories

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  Then Courses should be displayed

  When User clicks on the first available course
  Then The course page URL should match the selected course URL

Examples:
  | Category       | SubCategory       |
  | IT & Software  | IT Certifications |

# =========================================================
# SCENARIO 2: Course Title Validation
# =========================================================

@navigation @title
Scenario Outline: Verify course navigation and title from different categories

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  Then Course list should be displayed

  When User clicks on the first course
  Then The opened course title should match the selected course

Examples:
  | Category     | SubCategory     |
  | Development  | Web Development |

# =========================================================
# SCENARIO 3: Apply Filters
# =========================================================

@filters
Scenario: Apply all filters and verify courses

  And User chooses "Lifestyle" category
  And User selects sort option
  And User filters courses with rating
  And User selects video duration
  And User selects topic
  And User selects subcategory filter
  And User selects level
  And User selects language
  And User selects price
  Then Filtered courses should be displayed

  When User clicks on the first available course
  Then The course page URL should match the selected course URL

# =========================================================
# SCENARIO 4: Subscription Flow
# =========================================================

@subscription
Scenario Outline: Validate course navigation for different categories

  Given User navigates to "https://www.udemy.com"
  When User clicks on Explore menu
  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  And User clicks on Trending courses
  And User selects the first visible course
  Then User should be navigated to the course detail page
  And Course title should match the selected course
  When User clicks on Subscribe/Trial button
  Then User should be redirected to authentication page

Examples:
  | Category               | SubCategory          |
  | Finance & Accounting  | Investing & Trading |

# =========================================================
# SCENARIO 5: Add to Cart and Checkout
# =========================================================

@cart @checkout
Scenario Outline: User adds course to cart and proceeds to checkout using email from Excel

  Given User opens Udemy website
  When User clicks on Explore and selects "<Category>"
  And User hovers on first course and clicks Add to Cart
  And User clicks on Go to Cart
  And User clicks on Proceed to Checkout
  Then User enters email from Excel

Examples:
  | Category       | SubCategory       |
  | IT & Software  | IT Certifications |