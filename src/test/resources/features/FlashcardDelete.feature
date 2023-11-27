Feature: User deletes a flashcard
  Scenario: There is an existing Flashcard with specified id (positive)
    Given There is an existing Flashcard with the specified id of 1
    When User makes a DELETE request to "/flashcards/1"
    Then The response status should be 200

  Scenario: There is no existing Flashcard with specified id
    Given There is no existing Flashcard with the specified id of 1
    When User makes a DELETE request to "/flashcards/1"
    Then The response status should be 400
