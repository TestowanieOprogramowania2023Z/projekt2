package pw.ee.testowanie2;

import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pw.ee.testowanie2.models.Set;
import pw.ee.testowanie2.models.SetCreateDTO;
import pw.ee.testowanie2.models.SetDTO;

import java.io.IOException;

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
}
