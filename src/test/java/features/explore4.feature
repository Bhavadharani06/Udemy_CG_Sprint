Feature:Select a course and click subscrption button 

Scenario Outline: Validate course navigation for different categories
  Given User navigates to "https://www.udemy.com"
  When User clicks on Explore menu
  And User chooses "<Category>"
  And User selects subcategory "<SubCategory>"
  And User clicks on Trending courses
  And User selects the first visible course
  Then User should be navigated to the course detail page
  And Course title should match the selected course
  When User clicks on Subscribe/Trial button
  Then User should be redirected to authentication page

Examples:

  | Category               | SubCategory           |
  | Finance & Accounting  | Investing & Trading  |