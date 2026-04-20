# Search → Course Detail → Add to Cart → Save for Later → Remove
# Test data driven from Excel

@US_007 @TS_042 @TS_043 @TS_045 @TS_047 @cart @high
Feature: Cart Management - Add, Save for Later, and Remove Course

  #SCENARIO 1
  Scenario: Search and add course to cart
    Given User is on the Udemy homepage
    When User searches for the course using test data
    Then the search results page should display relevant courses
    When User clicks the first course link and switches to new tab if opened
    Then User should be on the course detail page
    When User scrolls and clicks "Add to cart"
    Then the "Go to cart" button should appear
    When User clicks the "Go to cart" button
    Then User should be on the cart page
    And the cart should contain the added course

  #SCENARIO 2
  Scenario: Save course for later from cart
    Given User has a course in the cart
    When User clicks "Save for Later"
    Then the course should be removed from the cart

  #SCENARIO 3
  Scenario: Remove course from cart
    Given User adds the course again to the cart
    And User navigates to the cart page
    When User clicks "Remove"
    Then the cart should be empty