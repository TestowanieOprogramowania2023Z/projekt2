Feature: User gets existing set by id
  Scenario: There is an existing Set with the specified id(positive)
    Given There is an existing Set with the specified id of 1
    When User makes a GET request to "/sets/1"
    Then the response status should be 200
    And The set with id of 1 should be returned
