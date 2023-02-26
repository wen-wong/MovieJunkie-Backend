package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ID0102_EditAccount {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    private ResponseEntity<String> response;

    private Account user;

    @Given("{string} is logged in")
    public void user_is_logged_in(String username) {
        // Log in a specific user
        user = accountRepository.findAccountByUsername(username);
        assertNotNull(user);
    }

    @When("the user changes their {string} to {string}")
    public void change_profile_info(String infoType, String newInfo) {
        // Attempt to set new info to info type
        if(infoType.equals("email")){
            user.setEmail(newInfo);
        }
        if(infoType.equals("password")){
            user.setPassword(newInfo);
        }
        AccountDTO userToModifyDTO = new AccountDTO(user);
        response = testRestTemplate.postForEntity("/account/edit", userToModifyDTO, String.class);
    }

    @Then("{string}'s {string} shall be {string}")
    public void user_info_shall_be(String username, String infoType, String infoValue) {
        // Check that <username>'s <infoType> is <infoValue>
        Account persistedUser = accountRepository.findAccountByUsername(username);
        assertNotNull(persistedUser);
        if(infoType.equals("email")){
            assertEquals(infoValue, persistedUser.getEmail());
        }else if(infoType.equals("password")){
            assertEquals(infoValue, persistedUser.getPassword());
        }else{
            fail("Test not implemented");
        }
    }

    @Then("there shall not exist an account with the username {string}")
    public void there_shall_not_exist_an_account_with_username(String username) {
        // Check that no accounts with given username exist
        assertNull(accountRepository.findAccountByUsername(username));
    }

    @And("the error message will be {string}")
    public void theErrorMessageWillBe(String errorMessage) {
        assertEquals(errorMessage, response.getBody());
    }
}
