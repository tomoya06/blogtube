package com.heavelop.blogtube.common.api;

public enum UserRole {
  INVALID(-1, "invalid"),
  USER(0, "user"),
  ADMIN(1, "admin"),
  VISITOR(2, "admin_visitor");

  private final int code;
  private final String message;

  UserRole(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public static String getMessage(int code) {
    UserRole[] roles = UserRole.values();
    for (UserRole userRole: roles) {
      if (userRole.getCode() == code) {
        return userRole.getMessage();
      }
    }
    return UserRole.INVALID.getMessage();
  }
}