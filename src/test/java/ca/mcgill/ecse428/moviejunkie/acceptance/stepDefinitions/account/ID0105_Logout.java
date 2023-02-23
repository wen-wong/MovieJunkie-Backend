package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ID0105_Logout {
    @Given("the user {string} is logged in")
    public void user_is_logged_in(String username) {
        // Login user
    }

    @When("the user attempts to log out")
    public void user_attempts_to_log_out() {
        // Log out user
    }
}
