package com.heavelop.blogtube.dto;

import lombok.Data;

@Data
public class UserResetPasswordParam {
  private String username;
  private String oldPassword;
  private String newPassword;
}