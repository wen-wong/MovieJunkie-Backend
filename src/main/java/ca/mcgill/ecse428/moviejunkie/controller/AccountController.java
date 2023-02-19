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

    @PostMapping(value= {"account/{username}/{email}/{password}","/account/{username}/{email}/{password}/"})
    @ResponseBody
    @ResponseStatus
    public AccountDTO createAccount (@PathVariable("username") String username,@PathVariable("email") String email,
                                     @PathVariable("password") String password) throws Exception {
        Account account = service.createAccount(username,password,email);
        return convertToDTO(account);
    }

    @PostMapping(value = {"/account/edit", "/account/edit/"})
    @ResponseBody
    public AccountDTO editAccount(@RequestBody AccountDTO request) throws AccountException {
        Account account = service.editAccount(request.getUsername(), request.getPassword(), request.getEmail());
        return convertToDTO(account);
    }

    @PostMapping(value = {"/account/delete", "/account/delete/"})
    @ResponseBody
    public void deleteAccount(@RequestBody AccountDTO request) throws AccountException {
        service.deleteAccount(request.getUsername(), request.getPassword());

    }

    private AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Cannot create account");
        }
        AccountDTO addressDTO = new AccountDTO();
//        addressDTO.setAccountId(account.getAccountId());
        addressDTO.setUsername(account.getUsername());
        addressDTO.setEmail(account.getEmail());
        addressDTO.setPassword(account.getPassword());
        return addressDTO;
    }
}
