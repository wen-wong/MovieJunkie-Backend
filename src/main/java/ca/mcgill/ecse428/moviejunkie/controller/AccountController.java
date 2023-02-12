package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.exceptions.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class AccountController
{
    @Autowired
    private AccountService service;


    @GetMapping(value = {"/account/{username}", "/account/{username}/"})
    public AccountDTO getAccount(@PathVariable("username") String username) throws IllegalArgumentException, AccountException {
        Account account = service.getAccount(username);
        return convertToDTO(account);
    }

    @PostMapping(value= {"/account/{firstName}/{lastName}/{username}/{password}/{email}","/account/{id}/{firstName}/{lastName}/{username}/{password}/{email}/"})
    @ResponseBody
    public AccountDTO createAccount(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName, @PathVariable("username") String username,
                                    @PathVariable("password") String password,@PathVariable("email") String email) throws Exception {
        Account account = service.createAccount(firstName, lastName, username,password,email);
        return convertToDTO(account);
    }

    private AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Cannot create account");
        }
        AccountDTO addressDTO = new AccountDTO();
        addressDTO.setAccountId(account.getAccountId());
        addressDTO.setFirstName(account.getFirstName());
        addressDTO.setLastName(account.getLastName());
        addressDTO.setUsername(account.getUsername());
        addressDTO.setEmail(account.getEmail());
        addressDTO.setPassword(account.getPassword());
        return addressDTO;
    }
}
