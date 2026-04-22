Feature: Udemy Course Navigation and Title Validation
Background:

  Scenario Outline: Verify course navigation and title from different categories

    Given User navigates to "https://www.udemy.com/"
    When User clicks on Explore menu
    And User chooses "<Category>"
    And User selects subcategory "<SubCategory>"
    Then Course list should be displayed

    When User clicks on the first course
    Then The opened course title should match the selected course

  Examples:
    | Category     | SubCategory     |
    | Development  | Web Development |
    #| Lifestyle | Arts & Crafts |
    #| Teaching & Academics | Social Science |
    