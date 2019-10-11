package com.heavelop.blogtube.dto;

import lombok.Data;

@Data
public class UserRegisterParam {
  private String username;
  private String password;
  private String email;
}