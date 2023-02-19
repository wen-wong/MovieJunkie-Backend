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
  public Account createAccount(String firstName, String lastName, String username, String password, String email)
      throws Exception {

    if (firstName == null) {
      throw new AccountException("firstName must not be null");
    }
    else if (lastName == null) {
      throw new AccountException("lastName must not be null");
    }
    else if (username == null) {
      throw new AccountException("username must not be null");
    }
    else if (password == null) {
      throw new AccountException("password must not be null");
    }
    else if (email == null) {
      throw new AccountException("email must not be null");
    }

    if (accountRepository.findAccountByUsername(username) != null) {
      throw new AccountException("account with username " + username + " already exists");
    }

    Account account = new Account(firstName, lastName, username, password, email);

    accountRepository.save(account);

    return account;
  }
  @Transactional
  public Account createAccount2(String name,String email, String password)
          throws Exception {

    if (name == null) {
      throw new AccountException("firstName must not be null");
    }
    else if (password == null) {
      throw new AccountException("password must not be null");
    }
    else if (email == null) {
      throw new AccountException("email must not be null");
    }

    Account account = new Account(name, password, email);

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
<<<<<<< HEAD

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
  public Account editAccount(String username, String password, String email) throws AccountException {
    Account account = accountRepository.findAccountByUsername(username);
    if (account == null) {
      throw new AccountException("Account not found");
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
}
