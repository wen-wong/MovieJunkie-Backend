package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void clearDatabase(){
        accountRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadAccount() {
        String username = "John123";
        String password = "password";
        String email = "john@mail.ca";

        Account john = new Account(username, password, email);

        john = accountRepository.save(john);

        String usernameID =john.getUsername();

        john = null;

        //read in from db to ensure it has been persisted
        john = accountRepository.findAccountByUsername(usernameID);

        assertNotNull(john);
        assertEquals(username, john.getUsername());
        assertEquals(password, john.getPassword());
        assertEquals(email, john.getEmail());
    }
}
