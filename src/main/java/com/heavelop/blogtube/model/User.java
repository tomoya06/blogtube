package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class User {
  private String name;
  private String avatarUrl;
  private String bio;
  private String createdTime;
  private String lastLoginTime;
  private String rank;
  private String userId;
  private Integer isBanned;
  private Integer isCanceled;
}