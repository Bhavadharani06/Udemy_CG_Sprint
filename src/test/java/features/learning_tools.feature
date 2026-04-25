@my_learnings
Feature: Learning Reminder Management

Background:
  Given User is on My Learning Page and Learning Tools

Scenario Outline: Create learning reminder
  When User navigates to Learning Tools
  Then User creates a learning reminder for courseName "<courseName>" freq "<freq>" time "<time>" and date "<date>"
  And the reminder should be visible on the Learning Tools page for "<courseName>"

Examples:
  | courseName | freq   | time  | date       |
  | Java       | Weekly | 12:00 |            |
  | Java       | Once   | 17:00 | 04/30/2026 |
  | Java       | Daily  | 10:00 |            |
  