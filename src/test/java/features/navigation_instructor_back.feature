# ETE_TC_003 – Search → Course Detail → Instructor Profile → Back Navigation
# Test data driven from Excel

@US_008 @TS_053 @navigation @highh
Feature: Back navigation retains search results
  
  Scenario: Navigate to instructor profile and validate back navigation
    Given User is on the Udemy homepage
    When User searches for the course using test data
    Then the search results page should display relevant courses
    When User clicks the first course link and switches to new tab if opened
    Then User should be on the course detail page
    And the course title should be visible
    When User captures and clicks the instructor link
    Then User should be navigated to the instructor profile section or page
    And the instructor name should match the course detail page
    When User clicks the browser back button
    Then User should be on the course detail page
    When User clicks the browser back button again
    Then User should be on the search results page
    And the search bar should contain the original keyword