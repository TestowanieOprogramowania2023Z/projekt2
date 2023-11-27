Feature: Managing Sets in the Web Service
  Scenario: User updates a set by id
    Given I have a set with id "1" with name "Not Set 1"
    When I update the set with id "1" with the name "Set 1"
    Then I should have a set with id "1" with the name "Set 1"
    And The response status code should be "200"

  Scenario: User updates a set by non existing id
    Given I have a set with id "1" with name "Set 1"
    When I update the set with id "2" with the name "Set 1"
    Then The response status code should be "404"