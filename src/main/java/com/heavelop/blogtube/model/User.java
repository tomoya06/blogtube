package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class User {
  private String username;
  private String password;
  private Integer createdTime;
  private String email;
  private Integer role;
  private Integer isBanned;
}