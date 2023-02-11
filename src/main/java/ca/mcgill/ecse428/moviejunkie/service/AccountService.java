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

    Account account = new Account(firstName, lastName, username, password, email);

    accountRepository.save(account);

    return account;
  }
}
