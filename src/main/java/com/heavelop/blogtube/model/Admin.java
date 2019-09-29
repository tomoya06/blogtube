package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class Admin {
  private String username;
  private String password;
  private String createdTime;
  private Integer role;
  private String adminId;
  private String bio;
}