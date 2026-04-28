@my_learnings
Feature: My Lists Management

  Background:
    Given User navigates to My Learning Page

  Scenario: Handle My Lists and verify list creation
    When User navigates to My Lists tab
    Then User handles My List flow with listName "Java List" and description "My learning list for Java Course"
    And User verifies the list is present in My Lists
    And User verifies list "Java List" is created
