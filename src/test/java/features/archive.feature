@my_learnings
Feature: Archive Management

  Background:
    Given User is on My Learning page

  Scenario: Handle archive dynamically and verify
    When User navigates to Archived tab
    Then User handles archive based on availability
    And User verifies archive state after action
