package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class ID0102_EditAccount {

    @Autowired
    private AccountRepository accountRepository;

    @Given("{string} is logged in")
    public void user_is_logged_in(String username) {
        // Log in a specific user
    }

    @When("the user changes their {string} to {string}")
    public void change_profile_info(String infoType, String newInfo) {
        // Attempt to set new info to info type
    }

    @Then("{string}'s {string} shall be {string}")
    public void user_info_shall_be(String username, String infoType, String infoValue) {
        // Check that <username>'s <infoType> is <infoValue>
        fail("Test not implemented");
    }

    @Then("there shall not exist an account with the username {string}")
    public void there_shall_not_exist_an_account_with_username(String username) {
        // Check that no accounts with given username exist
        assertNull(accountRepository.findAccountByUsername(username));
    }
}
