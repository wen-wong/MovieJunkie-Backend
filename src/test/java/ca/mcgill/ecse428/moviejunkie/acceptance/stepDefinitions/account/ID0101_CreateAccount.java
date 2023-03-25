package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import ca.mcgill.ecse428.moviejunkie.controller.ErrorObj;
import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.exceptions.AccountException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ID0101_CreateAccount {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

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

    @Given("the following accounts exist in the system:")
    public void there_are_X_accounts_in_the_system(DataTable dataTable) throws Exception {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        // Create accounts from rows
        for (Map<String, String> row: rows) {
            String username = row.get("username");
            String email = row.get("email");
            String password = row.get("password");

            accountService.createAccount(username, email, password);
        }
    }

    @When("an account is created with the following information: {string}, {string}, and {string}")
    public void an_account_is_created_with_the_following_info(String username, String email, String password) {
        response = testRestTemplate.postForEntity("/account/create", new AccountDTO(username, password, email), String.class);
    }

    @Then("there shall be {int} accounts in the system")
    public void there_shall_be_X_accounts(int numAccount) {
        // Check number of accounts
        assertEquals(numAccount, accountRepository.count());
    }

    @Then("there shall exist an account with the username {string}")
    public void there_shall_exist_an_account_with_username(String username) {
        // Check if account with username exists
        assertNotNull(accountRepository.findAccountByUsername(username));
    }

    @Then("the error message shall be {string}")
    public void the_error_message_shall_be(String errorMessage) throws JSONException {
        // Check if error message is correct
        JSONObject jsonObject = new JSONObject(response.getBody());
        assertEquals(errorMessage, jsonObject.getString("errorMsg"));
    }
}
