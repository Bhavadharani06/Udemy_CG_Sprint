# ETE_TC_002 – Search → Open Course → Add All To Cart → Verify No Duplicates
# Test data driven from Excel

@US_007 @TS_047 @TS_048 @cart @high
Feature: Add All To Cart and Verify No Duplicates

  Scenario: Add all items to cart and ensure no duplicates
    Given User is on the Udemy homepage
    When User searches for the course using test data
    Then the search results page should display relevant courses
    When User clicks the first course link and switches to new tab if opened
    Then User should be on the course detail page
    When User scrolls and clicks "Add all to cart"
    Then the "Go to cart" button should appear
    When User navigates to the cart page
    Then the cart should contain at least one course
    And there should be no duplicate courses in the cart
    When User navigates back and tries adding the same course again
    Then the cart item count should not increase