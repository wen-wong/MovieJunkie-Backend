package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.AccountDTO;
import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.exceptions.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value= {"account/create","/account/create/"})
    @ResponseBody
    public ResponseEntity createAccount(@RequestBody AccountDTO request) {
        try {
            Account account = service.createAccount(request.getUsername(), request.getEmail(), request.getPassword());

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(account);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorObj(e.getMessage())); // TODO: Change this into a JSON serializable object.
                                                                                       // A String cannot be serialized well int JSON
        }
    }

    @PostMapping(value = {"/account/edit", "/account/edit/"})
    @ResponseBody
    public ResponseEntity editAccount(@RequestBody AccountDTO request) {
        try{
            Account account = service.editAccount(request.getUsername(), request.getPassword(), request.getEmail());
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(convertToDTO(account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = {"/account/delete", "/account/delete/"})
    @ResponseBody
    public ResponseEntity deleteAccount(@RequestBody AccountDTO request) throws AccountException {
        try {
            service.deleteAccount(request.getUsername(), request.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value= {"account/login","/account/login/"})
    @ResponseBody
    public ResponseEntity login(@RequestBody AccountDTO request) {
        try {
            Account account = service.login(request.getUsername(), request.getEmail(), request.getPassword());

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(convertToDTO(account));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorObj(e.getMessage()));
        }
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
