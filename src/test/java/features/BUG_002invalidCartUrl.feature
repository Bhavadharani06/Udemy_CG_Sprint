@invalidurl
Feature: Validate 404 page for invalid cart URL
Scenario: Verify 404 page for invalid cart URL
  Given User is on the Udemy homepage
  When User enters invalid cart URL
  Then User should see a 404 error page