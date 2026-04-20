Feature: All Courses functionality

Background:
  Given user is on My Learning page

Scenario: Verify course is present
  Then user should see course "Java" in All Courses page

Scenario: Verify course is NOT present
  Then user should not see course "Python123" in All Courses page

Scenario: Verify no courses available
  Then user should see "Start learning" message in All Courses page