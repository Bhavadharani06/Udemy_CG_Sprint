Feature: Udemy Course Navigation Validation

  Scenario Outline: Check and validate for multiple categories

    Given User navigates to "https://www.udemy.com/"
    When User clicks on Explore menu
    And User chooses "<Category>"
   And User selects subcategory "<SubCategory>"
    Then Courses should be displayed

    When User clicks on the first available course
    Then The course page URL should match the selected course URL

  Examples:
    | Category       | SubCategory        |
    | IT & Software | IT Certifications  |
   # | Lifestyle | Arts & Crafts |
   # | Teaching & Academics | Social Science |