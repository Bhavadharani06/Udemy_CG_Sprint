@searchfilter
Feature: Udemy Course Flow (Full Excel Integration)

  Background:
    Given user is on Udemy homepage

  # Scenario 1: Standard search with multiple data sets from Excel
  Scenario Outline: Add various courses to cart from Excel
    When user searches for course at row <row_index> from sheet "CourseData"
    And user clicks on Add to Cart
    Then course should be added to cart

    Examples:
      | row_index |
      | 1         |
      | 2         |

  # Scenario 2: Testing Free Course filter using Excel data
  Scenario: Enroll in free course from Excel
    When user searches for course at row 3 from sheet "CourseData"
    And user applies free course filter
    Then enroll now button should be visible

  # Scenario 3: Complex filter logic using Excel data
  Scenario: Clear filters and add to cart from Excel
    When user searches for course at row 4 from sheet "CourseData"
    And user applies certification, rating and language filters
    And user clears all filters
    And user clicks on Add to Cart
    Then course should be added to cart

  # Scenario 4: Negative testing using Excel data
  Scenario: Search with invalid keyword from Excel
    When user searches for course at row 5 from sheet "CourseData"
    Then no courses should be displayed

  # Scenario 5: Random course verification
  Scenario: Verify search results for miscellaneous course
    When user searches for course at row 6 from sheet "CourseData"
    Then results should be displayed for the searched course