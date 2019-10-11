package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class User {
  private Long id;
  private String username;
  private String password;
  private String email;
  private Long createdTime;
  private Integer role;
  private String rolename;
  private Boolean isBanned;
}