package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class Admin {
  private String name;
  private String createdTime;
  private Integer role;
  private String adminId;
  private String bio;
}