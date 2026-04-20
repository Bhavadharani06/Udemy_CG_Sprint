Feature: All Courses functionality

Background:
 	Given user is on My Learning page

  Scenario: Verify course is present in All Courses page 
    Then user should see course "Java" in All Courses page

  Scenario: Verify course is NOT present in All Courses page
    Then user should not see course "Python123" in All Courses page