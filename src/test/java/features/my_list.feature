Feature: My Lists Management

Background:
  Given User is on My Learning page and My List

Scenario: Create list when My Lists is empty
  When User navigates to My Lists tab
  Then User handles My List flow with listName "Java List" and description "My learning list for Java Course"

Scenario: Verify existing list in My Lists
  When User navigates to My Lists tab
  Then User verifies list is already present