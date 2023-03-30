package ca.mcgill.ecse428.moviejunkie.controller;

public class ErrorObj {
  String errorMsg;
  public ErrorObj(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
