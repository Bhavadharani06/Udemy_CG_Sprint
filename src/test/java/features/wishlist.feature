@my_learnings
Feature: Wishlist Management

  Background:
    Given User is on My Learning page

  Scenario: Handle wishlist dynamically and verify
    When User navigates to Wishlist tab
    Then User manages wishlist based on availability
    And User verifies wishlist is not empty
    And User verifies course "Java" is present in wishlist