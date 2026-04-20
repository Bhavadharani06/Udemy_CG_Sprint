Feature: My Lists Management

Background:
  Given User is logged in
  And User is on My Learning page

Scenario: Create list when My Lists is empty
  When User navigates to My Lists tab
  Then If no list is present, user clicks on Go to All Courses
  And User selects a course and opens three dot menu
  And User clicks on Create New List
  And User enters list name and description
  And User clicks on Create button
  Then New list should be created successfully

Scenario: Verify existing list in My Lists
  When User navigates to My Lists tab
  Then If list exists, user verifies list is displayed