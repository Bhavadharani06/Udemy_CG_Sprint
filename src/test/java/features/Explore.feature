@udemy_all
Feature: Udemy End-to-End Validation Suite

Background:
  Given User navigates to "https://www.udemy.com/"
  When User clicks on Explore menu


# =========================================================
# SCENARIO 1: URL VALIDATION
# =========================================================

@navigation @url
Scenario Outline: Check and validate for multiple categories

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  Then Courses should be displayed

  When User clicks on the first available course
  Then The course page URL should match the selected course URL

Examples:
  | Category      | SubCategory       |
  | IT & Software | IT Certifications |


# =========================================================
# SCENARIO 2: TITLE VALIDATION
# =========================================================

@navigation @title
Scenario Outline: Verify course navigation and title from different categories

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  Then Courses should be displayed

  When User clicks on the first available course
  Then The opened course title should match the selected course

Examples:
  | Category    | SubCategory     |
  | Development | Web Development |


# =========================================================
# SCENARIO 3: FILTERS
# =========================================================

@filters
Scenario: Apply all filters and verify courses

  And User chooses "Lifestyle"
  And User selects sort option
  And User filters courses with rating
  And User selects video duration
  And User selects topic
  And User selects subcategory filter
  And User selects level
  And User selects language
  #And User selects price

  Then Courses should be displayed

  When User clicks on the first available course
  Then The course page URL should match the selected course URL


# =========================================================
# SCENARIO 4: SUBSCRIPTION FLOW
# =========================================================

@subscription
Scenario Outline: Validate subscription flow

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  And User clicks on Trending courses
  And User selects the first visible course

  Then User should be navigated to the course detail page
  And Course title should match the selected course

  When User clicks on Subscribe/Trial button
 Then User should be redirected to payment page

Examples:
  | Category              | SubCategory          |
  | Finance & Accounting | Investing & Trading |


# =========================================================
# SCENARIO 5: CART FLOW
# =========================================================

@cart @checkout
Scenario Outline: Add course to cart and checkout

  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"

  And User hovers on first course and clicks Add to Cart
  #And User clicks on Go to Cart
  And User clicks on Proceed to Checkout
  Then User should be redirected to payment page
  #Then User enters email from Excel

Examples:
  | Category      | SubCategory       |
  | IT & Software | IT Certifications |