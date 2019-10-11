package com.heavelop.blogtube.dto;

import lombok.Data;

@Data
public class UserLoginViaEmailAndCodeParam {
  private String email;
  private String code;
}