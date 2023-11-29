Feature: Managing Sets in the Web Service

  Scenario: Retrieving a set by name, which is not blank
    Given There is an existing Set with the specified name "newSet"
    When User makes a GET request to "sets/byName/newSet"
    Then the response status should be 200
    And the response body should contain a set with name "newSet"

  Scenario: Retrieving a set by name, which is blank
    When User makes a GET request to "sets/byName/"
    Then the response status should be 404

  Scenario: Retrieving a set by name, which is too long
    When User makes a GET request to "sets/byName/" with too long name
    Then the response status should be 400

  Scenario: Retrieving all sets sorted by name
    Given There are min two existing sets in the database
    When User makes a GET request to "sets/byName"
    Then the response status should be 200
    And the response body should contain list of sets sorted by name

  Scenario: Retrieving all sets sorted by creation date
    Given There are min two existing sets in the database
    When User makes a GET request to "sets/byCreatedAt"
    Then the response status should be 200
    And the response body should contain list of sets sorted by creation date

  Scenario: Retrieving all sets sorted by flashcards count
    Given There are min three existing sets in the database that contain flashcards
    When User makes a GET request to "sets/byFlashcardsCount"
    Then the response status should be 200
    And the response body should contain list of sets sorted by flashcards count