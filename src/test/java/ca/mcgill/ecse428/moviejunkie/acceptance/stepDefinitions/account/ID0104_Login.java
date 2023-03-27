package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

public class ID0104_Login {
  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private AccountRepository accountRepository;

  ResponseEntity<String> response;

  @Before
  public void setUp() {
    accountRepository.deleteAll();
  }

  @After
  public void teardown() {
    accountRepository.deleteAll();
    response = null;
  }

  @When("a user is on the log in page")
  public void aUserIsOnTheLogInPage() {
  }

  @And("the user enters a valid {string}, and {string}")
  public void theUserEntersAValidAnd(String username, String password) {
    response = testRestTemplate.postForEntity("/account/login", new AccountDTO(username, password, null), String.class);
  }

  @Then("the system will be logged into the account")
  public void theSystemWillBeLoggedIntoTheAccount() throws JSONException {
    JSONObject jsonObject = new JSONObject(response.getBody());

    try {
      assertNotNull(jsonObject.getString("username"));
    } catch (JSONException e) {
      fail();
    }
  }

  @And("a user enters an invalid {string} or {string}")
  public void aUserEntersAnInvalidOr(String username, String password) {
    response = testRestTemplate.postForEntity("/account/login", new AccountDTO(username, password, null), String.class);
  }

  @Then("the system will give an error message")
  public void theSystemWillGiveAnErrorMessage() throws JSONException {
    JSONObject jsonObject = new JSONObject(response.getBody());
    String errorMsg;
    try {
      errorMsg = jsonObject.getString("errorMsg");
    } catch (JSONException e) {
      errorMsg = null;
    }

    assertNotNull(errorMsg);
  }

  @And("the account shall not be authenticated")
  public void theAccountShallNotBeAuthenticated() throws JSONException {
    JSONObject jsonObject = new JSONObject(response.getBody());

    try {
      String errorMsg = jsonObject.getString("errorMsg");
      assertNotNull(errorMsg);
    } catch (JSONException e) {
      fail();
    }
  }
}
