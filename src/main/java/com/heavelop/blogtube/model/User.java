package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class User {
  private String username;
  private String password;
  private Long createdTime;
  private String email;
  private Integer role;
  private Boolean isBanned;
}