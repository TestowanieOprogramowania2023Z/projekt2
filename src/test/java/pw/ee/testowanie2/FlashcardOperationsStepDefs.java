package pw.ee.testowanie2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardCreateDTO;

public class FlashcardOperationsStepDefs extends SpringIntegrationTest {

  @Given("There is an existing Flashcard with the specified id of {int}")
  public void thereIsAnExistingFlashcardWithIdOf(int arg0) {
    if (!flashcardRepository.existsById((long) arg0)) {
      Flashcard flashcardEntity = Flashcard.builder()
          .id((long) arg0)
          .build();
      flashcardRepository.save(flashcardEntity);
    }
  }

  @Given("There is no existing Flashcard with the specified id of {int}")
  public void thereIsANoExistingFlashcardWithIdOf(int arg0) {
    if (!flashcardRepository.existsById((long) arg0)) {
      flashcardRepository.deleteById((long) arg0);
    }
  }

  @When("User makes a DELETE request to {string}")
  public void userMakesADELETERequestTo(String arg0) throws IOException {
    executeDelete(arg0);
  }
  @When("User provides Flashcard with contents of front {string}, back {string} and set id of {int}")
  public void userProvidesFlashcardWithAllContentsOf(String arg0, String arg1, int arg2){
    if (flashcardRepository.existsById((long) arg2)) {
      FlashcardCreateDTO flashcardCreateDTO = FlashcardCreateDTO.builder()
          .front(arg0)
          .back(arg1)
          .setId((long) arg2)
          .build();
      Gson gson = new Gson();
      bodyJSON = gson.toJson(flashcardCreateDTO);
    }
  }

  @And("User makes a UPDATE request to {string}")
  public void userMakeAPUTRequestTo(String arg0) throws IOException{
    executePut(arg0);
  }


  @Then("The response status should be {int}")
  public void theResponseStatusShouldBe(int arg0) {
    assertEquals(arg0, latestResponse.statusCode());
  }

}
