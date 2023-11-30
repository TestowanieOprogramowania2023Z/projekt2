Feature: User creates a set 
  Scenario: There is no existing Set with the specified name which is not blank (positive)
    When User provides a set name of "test set"
    And User makes a POST request to "sets"
    Then the response status should be 201
    And The Set with name of "test set" should exist in the database
    
  Scenario: There is no existing Set with the specified name which is blank
    When User provides a set name of ""
    And User makes a POST request to "sets"
    Then the response status should be 400
    
  Scenario: There is an existing Set with "name"
    Given There is an existing Set with name of "name"
    When User provides a set name of "name"
    And User makes a POST request to "sets"
    Then the response status should be 400