Feature: Managing Flashcards in the Web Service
  Scenario: User gets all existing flashcards from a existing set by non empty set name
    Given there is a set with the name "Set1"
    And there are flashcards in the set with the name "Set1"
    When the user requests all flashcards from the set with the name "Set1"
    Then the user gets all flashcards from the set with the name "Set1"
    And the user gets a status code 200
  Scenario: User get flashcard by id which exists
    Given there is a flashcard with the id "1"
    When the user requests the flashcard with the id "1"
    Then the user gets the flashcard with the id "1"
    And the user gets a status code 200
  Scenario: User get flashcard by id which does not exist
    Given there is no flashcard with the id "1"
    When the user requests the flashcard with the id "1"
    Then the user gets a status code 404