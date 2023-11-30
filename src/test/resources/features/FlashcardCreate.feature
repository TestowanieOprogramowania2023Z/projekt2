Feature: User creates a flashcard
  Scenario: User creates a flashcard assigned to an existing set
    Given there is a set which id is 1 and name of "Set 1"
    When User provides a flashcard with contents of front "front", back "back", setid of 1
    And User send POST request to "flashcards"
    Then The response status should be 201

  Scenario: User creates a flashcard assigned to an non-existing set
    Given there is no set of id 100
    When User provides a flashcard with contents of front "front", back "back", setid of 100
    And User send POST request to "flashcards"
    Then The response status should be 400

  Scenario: User creates a flashcard with blank front
    Given there is a set which id is 1
    When User provides a flashcard with contents of back "back", setid of 1
    And User send POST request to "flashcards"
    Then The response status should be 400

  Scenario: User creates a flashcard with blank back
    Given there is a set which id is 1
    When User provides a flashcard with contents of front "front", setid of 1
    And User send POST request to "flashcards"
    Then The response status should be 400
