package pw.ee.testowanie2;

import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardDTO;
import pw.ee.testowanie2.models.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashcardOperationsStepDefs extends SpringIntegrationTest {
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
}
