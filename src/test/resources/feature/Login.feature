Feature: Login

  Scenario Outline: Login as standard_user
    When user visits the site
    And login as "<userRef>" user
    Then user sees the list of items
    Examples:
      | userRef |
      | user1   |
      | user2   |