Feature: Learning Reminder Management

Background:
  Given User is on My Learning Page and Learning Tools

Scenario: Create learning reminder
  When User navigates to Learning Tools
  Then User creates a learning reminder for courseName "Java" freq "Once" time "17:00" and date "04/30/2026"