Feature: Login

  Scenario: Login as standard_user
    When user visits the site
    And login as "standard_user" user
    Then user sees the list of items

  Scenario: Login as performance_glitch_user
    When user visits the site
    And login as "performance_glitch_user" user
    Then user sees the list of items