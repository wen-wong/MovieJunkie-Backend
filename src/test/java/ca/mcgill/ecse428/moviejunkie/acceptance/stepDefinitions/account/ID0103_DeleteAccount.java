package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.account;

import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class ID0103_DeleteAccount {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    private ResponseEntity<String> response;

    @When("the user {string} attempts to delete their account")
    public void user_attempts_to_delete_their_account(String username) {
        // Get account info
        Account userToDelete = accountRepository.findAccountByUsername(username);
        AccountDTO userToDeleteDTO = new AccountDTO(userToDelete.getUsername(), userToDelete.getPassword(), userToDelete.getEmail());

        // Call REST API to delete account
        response = testRestTemplate.postForEntity("/account/delete", userToDeleteDTO, String.class);
    }
}
