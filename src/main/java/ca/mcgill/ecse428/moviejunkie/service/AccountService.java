package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import ca.mcgill.ecse428.moviejunkie.service.exceptions.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  @Transactional
  public Account createAccount(String username,String email, String password)
          throws Exception {

    if (email == null || email.equals("")) {
      throw new AccountException("email must be provided");
    }
    else if (username == null || username.equals("")) {
      throw new AccountException("username must be provided");
    }
    else if (password == null || password.equals("")) {
      throw new AccountException("password must be provided");
    }
    Account account = accountRepository.findAccountByUsername(username);

    if(account !=null){
      throw new AccountException("username already exists");
    }

    account=accountRepository.findAccountByEmail(email);
    if(account!=null){
      throw new AccountException("email already in use");
    }

    account = new Account(username, password, email);

    accountRepository.save(account);

    return account;
  }
  @Transactional
  public Account getAccount(String username) throws AccountException {
      Account account=accountRepository.findAccountByUsername(username);
      if(account==null){
        throw new AccountException("Account not found");
      }
      else{
        return account;
      }
  }

  @Transactional
  public void deleteAccount(String username, String password) throws AccountException {
      Account account=accountRepository.findAccountByUsername(username);
      String pass = account.getPassword();

      if(account==null){
        throw new AccountException("Account not found");
      } else if (password == null){
        throw new AccountException("Password must not be null");
      } else if (!password.equals(pass)){
        throw new AccountException("Sorry, the password you have entered is incorrect");
      } else {
        accountRepository.delete(account);
      }

  }

  @Transactional
  public Account editAccount(String username, String password, String email) throws Exception {
    Account account = accountRepository.findAccountByUsername(username);
    if (account == null) {
      throw new AccountException("Account not found");
    }
    Account account2 = accountRepository.findAccountByEmail(email);
    if (account2 != null && !(account.getUsername().equals(account2.getUsername()))) {
      throw new AccountException("Email already in use");
    } else {
      account.setPassword(password);
      account.setEmail(email);
      account = accountRepository.save(account);
    }
    return account;
  }

  public Account getAccountByEmail(String email) throws AccountException {
    Account account=accountRepository.findAccountByEmail(email);
    if(account==null){
      throw new AccountException("Account not found");
    }
    else{
      return account;
    }
  }

  public Account login(String username, String email, String password) throws AccountException {
    if (username == null && email == null) {
      throw new AccountException("Please input your username or email.");
    }

    if (password == null) {
      throw new AccountException("Please input your password");
    }

    Account account;

    if (username != null) {
      account = accountRepository.findAccountByUsername(username);

      if (account == null) {
        throw new AccountException("Account with username " + username + " does not exist.");
      }

      if (account.getPassword().equals(password)) {
        return account;
      }

      else {
        throw new AccountException("Password does not match password for username " + username + ".");
      }
    }

    else {
      account = accountRepository.findAccountByEmail(email);

      if (email == null) {
        throw new AccountException("Account with email " + email + " does not exist.");
      }

      if (account.getPassword().equals(password)) {
        return account;
      }

      else {
        throw new AccountException("Password does not match password for email " + email + ".");
      }
    }
  }
}
