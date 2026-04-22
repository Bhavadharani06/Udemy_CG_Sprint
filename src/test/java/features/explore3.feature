Feature: Apply filters and refine course search on Udemy
Background:

  Scenario: Apply all filters and verify courses

    Given User navigates to "https://www.udemy.com/"
    When User clicks on Explore menu
    And User chooses "Lifestyle" category
    And User selects sort option
    And User filters courses with rating
    And User selects video duration
    And User selects topic
    And User selects subcategory filter
    And User selects level
    And User selects language
    And User selects price
    #When User clicks on the first course
    Then Filtered courses should be displayed

    When User clicks on the first available course
    Then The course page URL should match the selected course URL