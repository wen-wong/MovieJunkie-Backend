package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ID0104_LogIn {

    @When("the user {string} attempts log in with password {string}")
    public void user_attempts_to_login_with_password(String username, String password) {
        // Attempt user login
    }

    @Then("user {string} shall be logged in")
    public void user_shall_be_logged_in(String username) {

    }

    @Then("user {string} shall not be logged in")
    public void user_shall_not_be_logged_in(String username) {

    }
}
