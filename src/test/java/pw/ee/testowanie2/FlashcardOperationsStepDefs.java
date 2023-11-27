package pw.ee.testowanie2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import pw.ee.testowanie2.models.Flashcard;

public class FlashcardOperationsStepDefs extends SpringIntegrationTest {

  @Given("There is an existing Flashcard with the specified id of {int}")
  public void thereIsAnExistingSetWithNameOf(int arg0) {
    if (!flashcardRepository.existsById((long) arg0)) {
      Flashcard flashcardEntity = Flashcard.builder()
          .id((long) arg0)
          .build();
      flashcardRepository.save(flashcardEntity);
    }
  }

  @When("User makes a DELETE request to {string}")
  public void userMakesAGETRequestTo(String arg0) throws IOException {
    executeDelete(arg0);
  }

  @Then("The response status should be {int}")
  public void theResponseStatusShouldBe(int arg0) {
    assertEquals(arg0, latestResponse.statusCode());
  }

}
