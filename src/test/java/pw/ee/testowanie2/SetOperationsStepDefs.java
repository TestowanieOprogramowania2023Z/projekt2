package pw.ee.testowanie2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.Set;
import pw.ee.testowanie2.models.SetDTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pw.ee.testowanie2.models.SetCreateDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetOperationsStepDefs extends SpringIntegrationTest {
    @When("User provides a set name of {string}")
    public void userProvidesASetNameOf(String arg0) {
        SetCreateDTO setEntity =  SetCreateDTO.builder()
                .name(arg0)
                .build();
        Gson gson = new Gson();
        bodyJSON = gson.toJson(setEntity);
    }

    @And("User makes a POST request to {string}")
    public void userMakesAPOSTRequestTo(String arg0) throws IOException {
        executePost(arg0);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int arg0) {
        assertEquals(arg0, latestResponse.statusCode());
    }

    @Given("There is an existing Set with name of {string}")
    public void thereIsAnExistingSetWithNameOf(String arg0) {
        if (!setRepository.existsByName(arg0)) {
            Set setEntity =  Set.builder()
                    .name(arg0)
                    .build();
            setRepository.save(setEntity);
        }
    }

    @Given("There is an existing Set with the specified id of {int}")
    public void thereIsAnExistingSetWithTheSpecifiedIdOf(int arg0) {
        if (!setRepository.existsById((long) arg0)) {
            Set setEntity =  Set.builder()
                    .id((long) arg0)
                    .build();
            setRepository.save(setEntity);
        }
    }

    @When("User makes a GET request to {string}")
    public void userMakesAGETRequestTo(String arg0) throws IOException {
        executeGet(arg0);
    }

    @And("The Set with name of {string} should exist in the database")
    public void theSetWithNameOfShouldExistInTheDatabase(String arg0) {
        assertTrue(setRepository.existsByName(arg0));
    }

    @And("The set with id of {int} should be returned")
    public void theSetWithIdOfShouldBeReturned(int arg0) {
        Gson gson = new Gson();
        SetDTO set = gson.fromJson(latestResponse.body(), SetDTO.class);
        assertEquals(arg0, set.getId());
    }

    @Given("There is an existing Set with the specified name {string}")
    public void thereIsAnExistingSetWithTheSpecifiedName(String arg0) {
        if (!setRepository.existsByName(arg0)) {
            Set setEntity =  Set.builder()
                    .name(arg0)
                    .build();
            setRepository.save(setEntity);
        }
    }

    @When("User makes a GET request to {string} with too long name")
    public void userMakesAGETRequestToWithTooLongName(String arg0) throws IOException {
        String longName = "sYWajyIjGrqABcRovPZsxkIieaehdZpWgTOCFNlUVKfLRqPuDyxlmMhsRwKuoHJvGXcxoOQmLaVgKzXqtlbLYkZovEvNtdAqEPgMiWtNDiytfZxMlmXxZnUdxLMFtmAMxtUIXKdvBQLuKVhzGjHQQJLoTZLWjTLvkZtPEBCrlpZtQYfDNMzWuXetOZXoPevTbcyURRrwqCtSVyPPNxWfcPKIXdQFtRNGWdfFEYLUvNwCjhVoJmAkzcrXcNdrJXWOGXsCWbvDNxctvcZKwLwDKbMxLkrRHNplpydjcvbDtciYhsebJqWXFCSwshNkTSuVoXgAeVzKusKpWsREpOcOaVRpcjHuTQjvdOHUuFSvFqxZtcBGTfcDaDpMfkGIXlHKwmvKtlxTgEeHKrHgLTAsxekLJlXQiexfmnIvXhCTdGUDirHGoQGSmqfiYFotjjWRkMuwshBBDIoOeljHnRPuBmKrLFDtdgjslUVTlwNFTHWYlEexzsmGgVJBrwOBrgZuHmEZjiRHFvONXatjGSlTeJSiWwFmFbJDBmEbIQoGvZmTNwXFhQTPAdbFYGhKmoaFBOWgZoqUgatVYGtzxunWcYwhLpRhGegVVOnMwvNpMQQgDMyQVkKdBfQZLxwNjvduiqAJaQTmjYoxAgNnZuXHIHMEWTIupBDvTqfCLjFJQeFATaLJjLbYmQyzpsRjbJioTLyCWflZuQVrvRDTtwOXqgDbydJjqTrNfzoMOKWJWBrxUoNUbSzngUmUJOpdPSkFiTOfjZCyVCFJyFpNxUwWEQASGnMnydxrUIyveUJhMoOmgviPwTICxYJEsGDWRgXGWKQtgJfZnljHsWYeVgUVRSZLpGgcbegfGqibPpXQqBYoZzyGzvcqJXyxBwfeebuDnNOflHOKlcUOdBhJIKBprwKKiYJXSoPMBZkKmoNUIljHLXUWezjfhgFpPZOvlKahfyZzlsajlqGPIELlkjXwiHRICoZvFkALdrZXbLoIfyFlPbHJhLFrwRsjHRlTeFkSKbNXnhrNcdofyYbJEvAfFJqzuzJANBlDFlLheJRLlTVENFyXYflVlNzJxZVWgulcMFaZMxfyzCFtkAOnvNIXJzPmvNltmIUfJwZXbZazgJawzJQZAKDCmcgBYJwcUHbGyDnGcEYDvBKxFlhVHIyKlwEeDkVLvjoNRKzAizJEWHEPTVYLsHuFYNFDkyZCXGZqJAJZuQnSEmlbvXgsUuCFyPNmdgRScJRyFtiYvXjJBGleMHokCZBpKzhfFQ";
        executeGet(arg0 + longName);
    }

    @And("the response body should contain a set with name {string}")
    public void theResponseBodyShouldContainASetWithName(String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SetDTO setDTO = objectMapper.readValue(latestResponse.body(), SetDTO.class);
        assertEquals(name, setDTO.getName());
    }

    @And("the response body should contain list of sets sorted by name")
    public void theResponseBodyShouldContainListOfSetsSortedByName() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SetDTO> sets = objectMapper.readValue(latestResponse.body()
                , new TypeReference<>() {});

        for (int i = 0; i < sets.size() - 1; i++) {
            SetDTO currentSet = sets.get(i);
            SetDTO nextSet = sets.get(i + 1);
            assertTrue(currentSet.getName().compareTo(nextSet.getName()) <= 0);
        }
    }

    @Given("There are min two existing sets in the database")
    public void thereAreMinTwoExistingSetsInTheDatabase() {

        String name1 = "abc";
        String name2 = "def";

        if (!setRepository.existsByName(name1)) {
            Set setEntity =  Set.builder()
                    .name(name1)
                    .createdAt(
                            Date.from(LocalDateTime.now().minusHours(1)
                                    .atZone(ZoneId.systemDefault()).toInstant())
                    )
                    .build();
            setRepository.save(setEntity);
        }

        if (!setRepository.existsByName(name2)) {
            Set setEntity =  Set.builder()
                    .name(name2)
                    .createdAt(
                            Date.from(LocalDateTime.now()
                                    .atZone(ZoneId.systemDefault()).toInstant())
                    )
                    .build();
            setRepository.save(setEntity);
        }
    }

    @And("the response body should contain list of sets sorted by creation date")
    public void theResponseBodyShouldContainListOfSetsSortedByCreationDate() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SetDTO> sets = objectMapper.readValue(latestResponse.body()
                , new TypeReference<>() {});
        for (int i = 0; i < sets.size() - 1; i++) {
            SetDTO currentSet = sets.get(i);
            SetDTO nextSet = sets.get(i + 1);
            assertTrue(currentSet.getCreatedAt().compareTo(nextSet.getCreatedAt()) <= 0);
        }
    }

    @Given("There are min three existing sets in the database that contain flashcards")
    public void thereAreMinThreeExistingSetsInTheDatabaseThatContainFlashcards() {

        String name1 = "abcd";
        String name2 = "efgh";
        String name3 = "ijkl";

        Flashcard flashcard1 = Flashcard.builder()
                .front("a")
                .back("b")
                .build();

        Flashcard flashcard2 = Flashcard.builder()
                .front("c")
                .back("d")
                .build();

        Flashcard flashcard3 = Flashcard.builder()
                .front("e")
                .back("f")
                .build();

        if (!setRepository.existsByName(name1)) {
            Set setEntity =  Set.builder()
                    .name(name1)
                    .build();
            setRepository.save(setEntity);
            flashcard1.setSet(setEntity);
            flashcardRepository.save(flashcard1);
            setRepository.save(setEntity);
        } else {

            Optional<Set> optionalSet = setRepository.findByName(name1);
            Set set = optionalSet.get();
            flashcard1.setSet(set);
            flashcardRepository.save(flashcard1);
            setRepository.save(set);
        }

        if (!setRepository.existsByName(name2)) {
            Set setEntity =  Set.builder()
                    .name(name2)
                    .build();
            setRepository.save(setEntity);
        }  else {
            Optional<Set> optionalSet = setRepository.findByName(name1);
            Set set = optionalSet.get();
            set.getFlashcards().clear();
            setRepository.save(set);
        }

        if (!setRepository.existsByName(name3)) {
            Set setEntity =  Set.builder()
                    .name(name3)
                    .build();
            setRepository.save(setEntity);
        } else {
            Optional<Set> optionalSet = setRepository.findByName(name1);
            Set set = optionalSet.get();
            flashcard2.setSet(set);
            flashcard3.setSet(set);
            flashcardRepository.save(flashcard2);
            flashcardRepository.save(flashcard3);
            set.getFlashcards().add(flashcard2);
            set.getFlashcards().add(flashcard3);
            setRepository.save(set);
        }

    }

    @And("the response body should contain list of sets sorted by flashcards count")
    public void theResponseBodyShouldContainListOfSetsSortedByFlashcardsCount() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<SetDTO> sets = objectMapper.readValue(latestResponse.body()
                , new TypeReference<>() {
                });

        for (int i = 0; i < sets.size() - 1; i++) {
            SetDTO currentSet = sets.get(i);
            SetDTO nextSet = sets.get(i + 1);
            assertTrue(currentSet.getFlashcardsCount()
                    .compareTo(nextSet.getFlashcardsCount()) >= 0);
        }
    }
    @And("The response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String arg0) {
        assertEquals(Integer.parseInt(arg0), latestResponse.statusCode());
    }

    @Given("I have a set with id {string} with name {string}")
    public void iHaveASetWithIdWithName(String arg0, String arg1) {
        Set setEntity =  Set.builder()
                .id(Long.parseLong(arg0))
                .name(arg1)
                .build();
        setRepository.save(setEntity);
    }

    @When("I update the set with id {string} with the name {string}")
    public void iUpdateTheSetWithIdWithTheName(String arg0, String arg1) throws IOException {
        SetDTO setEntity =  SetDTO.builder()
                .id(Long.parseLong(arg0))
                .name(arg1)
                .build();
        Gson gson = new Gson();
        bodyJSON = gson.toJson(setEntity);
        executePut("sets/" + arg0);
    }

    @Then("I should have a set with id {string} with the name {string}")
    public void iShouldHaveASetWithIdWithTheName(String arg0, String arg1) {
        Set set = setRepository.findById(Long.parseLong(arg0)).get();
        assertEquals(arg1, set.getName());
    }

    @Given("The set with id of {int} does not exist")
    public void theSetWithIdOfDoesNotExist(int arg0) {
        if (setRepository.existsById((long) arg0)) {
            setRepository.deleteById((long) arg0);
        }
    }
}
