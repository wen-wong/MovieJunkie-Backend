package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionStepDefinitions {

    @Autowired
    private TestRestTemplate template;

    ResponseEntity<String> response;

    @When("the client calls {string}")
    public void theClientCallsVersion(String call) {
        response = template.getForEntity(call, String.class);
    }

    @Then("the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCode(int code) {
        assertEquals(response.getStatusCode().value(), code);
    }

    @And("the client receives server version (.+)$")
    public void theClientReceivesServerVersion(String version) {
        assertEquals(response.getBody(), version);
    }

}
