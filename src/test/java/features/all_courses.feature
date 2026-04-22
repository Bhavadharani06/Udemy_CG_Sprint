# src/test/resources/features/AllCourses.feature

@AllCourses
Feature: All Courses - My Learning

  # BACKGROUND
  # After login, navigate directly to All Courses (no click on tab needed)
  Background:
    Given user is logged in and navigated to All Courses page

  # SCENARIO 1 : No courses enrolled
  @NoCourse
  Scenario: Verify no courses message when user has no enrolled courses
    Then user should see Start learning message

  # SCENARIO 2 : Courses present (single course check) 
  @CourseCheck
  Scenario Outline: Verify specific course is present in All Courses
    Then user should see course "<courseName>" in All Courses page

    Examples:
      | courseName                  |
      | Java                        |
      | Java Database Connection    |
      | Selenium                    |

  # SCENARIO 3 : Course NOT present
  @CourseAbsent
  Scenario: Verify course that is NOT enrolled is absent
    Then user should NOT see course "Python123" in All Courses page

  # SCENARIO 4 : Full flow — play video, change speed/volume, go back
  # Data-table drives speed and quality options in one scenario
  @VideoFlow
  Scenario: Play first course video, change speed and quality, then go back
    Given user has at least one course enrolled
    When  user clicks on the first course
    And   user plays the video
    And   user changes playback settings with the following options:
      | settingType | value  |
      | speed       | 5      |
      | speed       | 7      |
      | speed       | 2      |
      | quality     | 720p   |
      | quality     | 1080p  |
      | quality     | Auto   |
    # speed index: 1=0.5x | 2=0.75x | 3=1x | 4=1.25x | 5=1.5x | 6=1.75x | 7=2x
    And   user toggles the volume
    Then  user should see the course title in the player
    When  user navigates back to All Courses page
    Then  user should see courses on the All Courses page

  # SCENARIO 5 : Data driven from Excel (course names + expected count)
  # Step reads test data from Excel — no inline examples needed here.
  @ExcelDriven
  Scenario: Verify course list using data from Excel
    Then user verifies courses from Excel file "TestData/TestData.xlsx" on sheet "Courses"

  # SCENARIO 6 : Assert course count
  @CourseCount
  Scenario: Verify expected number of courses
    Then user should see exactly 3 courses in All Courses page

  # SCENARIO 7 : Speed options driven by DataTable
  @SpeedOptions
  Scenario: Verify all speed options can be selected on first course video
    Given user has at least one course enrolled
    When  user clicks on the first course
    And   user plays the video
    Then  user should be able to select each speed from the list:
      | speedIndex |
      | 1          |
      | 2          |
      | 3          |
      | 4          |
      | 5          |
      | 6          |
      | 7          |
    # 1=0.5x | 2=0.75x | 3=1x | 4=1.25x | 5=1.5x | 6=1.75x | 7=2x
    And user navigates back to All Courses page
