Feature: Udemy Course Flow

  Background:
    Given user is on Udemy homepage

  # Scenario Outline (same flow)
  Scenario Outline: Add course to cart without filters
    When user searches for "<course>"
    And user clicks on Add to Cart
    Then course should be added to cart

    Examples:
      | course              |
      | selenium webdriver  |
      | Java                |

  # Keep separate (different flow)
  Scenario: Enroll in free course
    When user searches for "Python"
    And user applies free course filter
    Then enroll now button should be visible

  Scenario: Clear filters and add to cart
    When user searches for "Sap"
    And user applies certification, rating and language filters
    And user clears all filters
    And user clicks on Add to Cart
    Then course should be added to cart

  Scenario: Search with invalid keyword
    When user searches for "asdfghjkl123"
    Then no courses should be displayed