@my_learnings
Feature: All Courses Management

  Background:
    Given user navigates directly to All Courses page

  # Scenario 1: Check courses present, open first, play video, go back, assert ──
  Scenario: Open first course play video and navigate back
    Then user should see at least one course in All Courses
    And user gets the first course title
    When user clicks on the first course
    And user plays the video
    Then user navigates back to All Courses page
    And user asserts course count is more than 0

  # Scenario 2: DataTable — verify specific courses from feature file ──
  Scenario: Verify multiple courses using DataTable
    Then user verifies the following courses are present:
      | courseName |
      | Java       |
      | Selenium   |

  # Scenario 3: Excel — verify courses from TestData.xlsx ──
  Scenario: Verify courses from Excel sheet
    Then user verifies courses from excel sheet "CourseData"

  # Scenario 4: Scenario Outline — verify each course one by one ──
  Scenario Outline: Verify course presence
    Then user should see course "<courseName>" in All Courses

    Examples:
      | courseName |
      | Java       |
      | Selenium   |

  # Scenario 5: No courses message check ──
  Scenario: Verify no courses message when nothing is enrolled
    Then user should see the no courses message
