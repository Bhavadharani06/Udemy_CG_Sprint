Feature: Udemy Course and Instructor Flow

  Background: 
    Given User launches the browser
    And User navigates to Udemy website

  Scenario: Search course and verify results
    When User searches for course from excel
    Then Search results should be displayed

  Scenario: Open first course and add to cart
    When User searches for course from excel
    And User selects the first course from results
    And User adds the course to cart
    Then Course should be added successfully

  Scenario: Navigate to instructor profile
    When User searches for course from excel
    And User selects the first course from results
    And User clicks on instructor profile
    Then Instructor profile page should be displayed

  Scenario: Verify LinkedIn profile opens
    When User searches for course from excel
    And User selects the first course from results
    And User clicks on instructor profile
    And User opens instructor LinkedIn profile
    Then LinkedIn page should open in a new tab

  Scenario: Verify YouTube channel opens
    When User searches for course from excel
    And User selects the first course from results
    And User clicks on instructor profile
    And User opens instructor YouTube channel
    Then YouTube channel should open successfully

  Scenario: Verify behavior for invalid course search
    Then An appropriate no results message should be displayed