package com.heavelop.blogtube.common.api;

public enum ResultCode {
  SUCCESS(200, "success"), 
  FAILED(500, "error"),
  FORBIDDEN(403, "forbidden");

  private final int code;
  private final String message;

  ResultCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }
}