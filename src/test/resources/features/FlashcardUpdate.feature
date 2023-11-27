Feature: User updates a flashcard
  Scenario: There is an existing Flashcard with specified id (positive)
    Given There is an existing Flashcard with the specified id of 1
    And There is an existing Set with the specified id of 1
    When User provides Flashcard with contents of front "front", back "back" and set id of 1
    And User makes a UPDATE request to "/flashcards/1"
    Then The response status should be 200

  Scenario: There is no existing Flashcard with specified id
    Given There is no existing Flashcard with the specified id of 1
    And There is an existing Set with the specified id of 1
    When User provides Flashcard with contents of front "front", back "back" and set id of 1
    And User makes a UPDATE request to "/flashcards/1"
    Then The response status should be 400

