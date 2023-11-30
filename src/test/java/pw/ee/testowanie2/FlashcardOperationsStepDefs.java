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
import pw.ee.testowanie2.models.FlashcardDTO;
import pw.ee.testowanie2.models.Set;

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
    @When("User makes a DELETE request to {string}")
    public void userMakesADELETERequestTo(String arg0) throws IOException {
        executeDelete(arg0);
    }
      
    @Then("The response status should be {int}")
    public void theResponseStatusShouldBe(int arg0) {
        assertEquals(arg0, latestResponse.statusCode());
    }
      
  @Given("There is no existing Flashcard with the specified id of {int}")
  public void thereIsANoExistingFlashcardWithIdOf(int arg0) {
    if (!flashcardRepository.existsById((long) arg0)) {
      flashcardRepository.deleteById((long) arg0);
    }
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

    @Given("there is a set with the name {string}")
    public void thereIsASetWithTheName(String arg0) {
        setRepository.save(Set.builder()
                .name(arg0)
                .build());
    }

    @And("there are flashcards in the set with the name {string}")
    public void thereAreFlashcardsInTheSetWithTheName(String arg0) {
        flashcardRepository.save(Flashcard.builder()
                .back("question")
                .front("answer")
                .set(setRepository.findByName(arg0).get())
                .build());
        flashcardRepository.save(Flashcard.builder()
                .back("question2")
                .front("answer2")
                .set(setRepository.findByName(arg0).get())
                .build());
    }

    @When("the user requests all flashcards from the set with the name {string}")
    public void theUserRequestsAllFlashcardsFromTheSetWithTheName(String arg0) throws IOException {
        executeGet("/flashcards/by-set-name/" + arg0);
    }

    @Then("the user gets all flashcards from the set with the name {string}")
    public void theUserGetsAllFlashcardsFromTheSetWithTheName(String arg0) {
        Gson gson = new Gson();
        FlashcardDTO[] flashcards = gson.fromJson(latestResponse.body(), FlashcardDTO[].class);
        assertEquals(2, flashcards.length);
        assertEquals("question", flashcards[0].getBack());
        assertEquals("answer", flashcards[0].getFront());
        assertEquals("question2", flashcards[1].getBack());
        assertEquals("answer2", flashcards[1].getFront());
    }

    @And("the user gets a status code {int}")
    public void theUserGetsAStatusCode(int arg0) {
        assertEquals(arg0, latestResponse.statusCode());
    }

    @Given("there is a flashcard with the id {string}")
    public void thereIsAFlashcardWithTheId(String arg0) {
        flashcardRepository.save(Flashcard.builder()
                .id(Long.parseLong(arg0))
                .build());
    }

    @When("the user requests the flashcard with the id {string}")
    public void theUserRequestsTheFlashcardWithTheId(String arg0) throws IOException {
        executeGet("/flashcards/" + arg0);
    }

    @Then("the user gets the flashcard with the id {string}")
    public void theUserGetsTheFlashcardWithTheId(String arg0) {
        Gson gson = new Gson();
        FlashcardDTO flashcard = gson.fromJson(latestResponse.body(), FlashcardDTO.class);
        assertEquals(Long.parseLong(arg0), flashcard.getId());
    }

    @Given("there is no flashcard with the id {string}")
    public void thereIsNoFlashcardWithTheId(String arg0) {
        if (flashcardRepository.existsById(Long.parseLong(arg0))) {
            flashcardRepository.deleteById(Long.parseLong(arg0));
        }
    }

    @Given("there is a set which id is {int} and name of {string}")
    public void thereIsASetWhichIdIsAndNameOf(int arg0, String arg1) {

        if (!setRepository.existsById((long) arg0)) {
            Set setEntity = Set.builder()
                    .id((long) arg0)
                    .name(arg1)
                    .build();
            setRepository.save(setEntity);
        }
    }

    @When("User provides a flashcard with contents of front {string}, back {string}, setid of {int}")
    public void userProvidesAFlashcardWithContentsOfFrontBackSetIdOf(String arg0, String arg1, int arg2) {
        FlashcardCreateDTO flashcardCreateDTO = FlashcardCreateDTO.builder()
                .front(arg0)
                .back(arg1)
                .setId((long) arg2)
                .build();
        Gson gson = new Gson();
        bodyJSON = gson.toJson(flashcardCreateDTO);
    }

    @And("User send POST request to {string}")
    public void userSendPostRequestTo(String string) throws IOException {
        executePost(string);
    }

    @Given("there is no set of id {int}")
    public void thereIsNoSetOfId(int arg0) {
        if (setRepository.existsById((long) arg0)) {
            setRepository.deleteById((long) (arg0));
        }
    }

    @When("User provides a flashcard with contents of back {string}, setid of {int}")
    public void userProvidesAFlashcardWithContentsOfBackSetidOf(String arg0, int arg1) {
        FlashcardCreateDTO flashcardCreateDTO = FlashcardCreateDTO.builder()
                .back(arg0)
                .setId((long) arg1)
                .build();
        Gson gson = new Gson();
        bodyJSON = gson.toJson(flashcardCreateDTO);
    }

    @When("User provides a flashcard with contents of front {string}, setid of {int}")
    public void userProvidesAFlashcardWithContentsOfFrontSetidOf(String arg0, int arg1) {
        FlashcardCreateDTO flashcardCreateDTO = FlashcardCreateDTO.builder()
                .back(arg0)
                .setId((long) arg1)
                .build();
        Gson gson = new Gson();
        bodyJSON = gson.toJson(flashcardCreateDTO);
    }

    @Given("there is a set which id is {int}")
    public void thereIsASetWhichIdIs(int arg0) {
        if (!setRepository.existsById((long) arg0)) {
            Set setEntity = Set.builder()
                    .id((long) arg0)
                    .build();
            setRepository.save(setEntity);
        }
    }
}
