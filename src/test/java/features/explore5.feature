Feature: Add course to cart and checkout

  Scenario: User adds course to cart and proceeds to checkout using email from Excel
    #Given User opens Udemy website
    When User clicks on Explore and selects "<Category>"
    And User hovers on first course and clicks Add to Cart
    And User clicks on Go to Cart
    And User clicks on Proceed to Checkout
    And  User enters email from Excel
   Then The application should navigate to checkout page
    
      Examples:
    | Category       | SubCategory        |
    | IT & Software | IT Certifications  |
   # | Lifestyle | Arts & Crafts |
   # | Teaching & Academics | Social Science |