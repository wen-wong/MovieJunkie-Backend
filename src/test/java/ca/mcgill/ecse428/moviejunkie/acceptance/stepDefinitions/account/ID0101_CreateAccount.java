package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0101_CreateAccount {
    @Given("the following accounts exist in the system:")
    public void there_are_X_accounts_in_the_system(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        // Create accounts from rows
        for (Map<String, String> row: rows) {
            String username = row.get("username");
            String firstName = row.get("firstName");
            // ...

            // Create accounts
        }
    }

    @When("an account is created with the following information: {string}, {string}, {string}, {string}, and {string}")
    public void an_account_is_created_with_the_following_info(String username, String firstName, String lastName, String email, String password) {
        // Create account with given parameters
    }

    @Then("there shall be {int} accounts in the system")
    public void there_shall_be_X_accounts(int numAccount) {
        // Check number of accounts
        assertTrue(true); // TODO: Remove once method implemented
    }

    @Then("there shall exist an account with the username {string}")
    public void there_shall_exist_an_account_with_username(String username) {
        // Check if account with username exists
        assertTrue(true); // TODO: Remove once method implemented
    }

    @Then("the error message shall be {string}")
    public void the_error_message_shall_be(String errorMessage) {
        // Check if error message is correct
        assertTrue(true); // TODO: Remove once method implemented
    }
}
