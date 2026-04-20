Feature: User Signup on Udemy

  Scenario: Successful signup with valid details

    Given launch the new browser
    And navigate to udemy using url "https://www.udemy.com/"
    
    When User clicks on Sign Up
    And User enters valid name "Dharani" and email "dharanivedha26@gmail.com"
    And User clicks Continue
    
    Then Verification code should be sent to registered email
    
    When User enters OTP manually and completes signup
    
    Then User should be redirected to homepage