Feature: Buy Items

  Scenario: Check number of item in the shopping cart
    Given "standard_user" user is logged in
    When user add all items to the cart
    Then user sees 6 items added to the cart

  Scenario: Check the total value of items in the shopping cart
    Given "standard_user" user is logged in
    When user add all items to the cart
    And user opens the cart
    And user proceeds to checkout from the cart
    And user continue checkout with the following info
      | field       | value |
      | First name  | John  |
      | Last name   | Doe   |
      | Postal code | 2145  |
    Then user sees the total value equals to 140.34 dollars

  Scenario Outline: Check the total value of items in the shopping cart when delete t-shirts
    Given "performance_glitch_user" user is logged in
    When user add all items to the cart
    And user opens the cart
    But user delete all the "<item>" from the cart
    And user continue checkout with the following info
      | field       | value |
      | First name  | John  |
      | Last name   | Doe   |
      | Postal code | 2145  |
    Then user sees the total value equals to <value> dollars

    Examples:
      | item     | value  |
      | T-Shirt  | 105.8  |
      | Backpack | 107.95 |

