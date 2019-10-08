package com.heavelop.blogtube.dto;

public class UserLoginViaEmailAndCodeParam {
  private String email;
  private String code;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}