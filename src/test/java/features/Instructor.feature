Feature: Udemy Course Purchase and Instructor Social Links Verification

Background:
  Given User launches Chrome browser



Scenario: Search for a Python course
  When User enters "python" in search bar
  And User presses Enter
  Then Search results should be displayed



Scenario: Add first course to cart
  When User enters "python" in search bar
  And User presses Enter
  And User clicks on the first course
  And User clicks on Add to Cart button
  Then Course should be added to cart



Scenario: Open instructor profile
  When User enters "python" in search bar
  And User presses Enter
  And User clicks on the first course
  And User clicks on instructor "Angela Yu"
  Then Instructor profile page should be displayed


Scenario: Open instructor LinkedIn profile
  When User enters "python" in search bar
  And User presses Enter
  And User clicks on the first course
  And User clicks on instructor "Angela Yu"
  And User clicks on LinkedIn link
  Then LinkedIn page should open in new tab


Scenario: Open instructor YouTube channel
  When User enters "python" in search bar
  And User presses Enter
  And User clicks on the first course
  And User clicks on instructor "Angela Yu"
  And User clicks on YouTube link
  Then YouTube channel should open successfully



Scenario: Search with invalid keyword
  When User enters "asdfghjkl12345" in search bar
  And User presses Enter
  Then No courses should be displayed