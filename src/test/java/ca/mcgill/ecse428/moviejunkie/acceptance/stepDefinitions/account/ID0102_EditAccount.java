package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0102_EditAccount {
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
        assertTrue(true); // TODO: Remove once method implemented
    }

    @Then("there shall not exist an account with the username {string}")
    public void there_shall_not_exist_an_account_with_username(String username) {
        // Check that no accounts with given username exist
        assertTrue(true); // TODO: Remove once method implemented
    }
}
