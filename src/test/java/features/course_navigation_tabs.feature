# TS_049 – Course Navigation via Thumbnail and URL Validation
# Test data driven from Excel

@TS_049 @navigation @medium
Feature: Course navigation and URL comparison across tabs

  Scenario: Open two courses and verify unique URLs
    Given User is on the Udemy homepage
    When User searches for the course using test data
    Then the search results page should display at least 2 courses
    When User clicks the first course link and captures its title
    Then User should be on the course detail page
    And User captures Course A URL
    And the course title should match the selected search result
    When User clicks the browser back button
    Then User should be on the search results page
    When User opens the second course in a new tab
    Then User should be on the course detail page in the new tab
    And User captures Course B URL
    Then Course A URL and Course B URL should be different
    And both tabs should remain open