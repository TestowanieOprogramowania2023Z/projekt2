package pw.ee.testowanie2;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pw.ee.testowanie2.models.Set;
import pw.ee.testowanie2.models.SetCreateDTO;
import pw.ee.testowanie2.models.SetDTO;

import java.io.IOException;
import java.util.Map;

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
        executePut("/sets/" + arg0);
    }

    @Then("I should have a set with id {string} with the name {string}")
    public void iShouldHaveASetWithIdWithTheName(String arg0, String arg1) {
        Set set = setRepository.findById(Long.parseLong(arg0)).get();
        assertEquals(arg1, set.getName());
    }

}
