@parallel @TS_044 @cart @navigation @high
Feature: Cart Persistence After Back Navigation and Refresh

  Scenario Outline: Verify cart count persists after navigation and refresh
    Given User is on the Udemy homepage
    When User clears the cart
    And User searches for "<keyword>"
    Then the search results page should display relevant courses
    When User clicks the first course link
    Then User should be on the course detail page
    When User clicks "Add to cart"
    Then the course should be added to the cart
    When User navigates to the cart page
    Then the cart should contain exactly <count> course
    When User clicks the browser back button
    Then User should be on the search results page
    And the search bar should contain the original keyword
    When User refreshes the page
    Then the cart count should still be <count>
    When User navigates to the cart page
    Then the cart should contain exactly <count> course

  Examples:
    | keyword | count |
    | Excel   | 1     |
    | Python  | 1     |