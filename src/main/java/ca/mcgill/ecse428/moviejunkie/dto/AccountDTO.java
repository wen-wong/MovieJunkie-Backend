package ca.mcgill.ecse428.moviejunkie.dto;

public class AccountDTO {
//  private int accountId;
  private String username;
  private String password;
  private String email;

  public AccountDTO(){}

  public AccountDTO(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

//  public int getAccountId() {
//    return accountId;
//  }
//
//  public void setAccountId(int accountId) {
//    this.accountId = accountId;
//  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
