package com.heavelop.blogtube.common.api;

public class CommonResult<T> {
  private int code;
  private String status;
  private T result;

  public CommonResult(Integer code, String status, T result) {
    this.code = code;
    this.status = status;
    this.result = result;
  }

  public static <T> CommonResult<T> success(T result) {
    return new CommonResult<T>(
      ResultCode.SUCCESS.getCode(),
      ResultCode.SUCCESS.getMessage(),
      result
    );
  }

  public static <T> CommonResult<T> failed(T result) {
    return new CommonResult<T>(
      ResultCode.FAILED.getCode(),
      ResultCode.FAILED.getMessage(),
      result
    );
  }

  public static <T> CommonResult<T> bad(T result) {
    return new CommonResult<T>(
      ResultCode.BAD.getCode(),
      ResultCode.BAD.getMessage(),
      result
    );
  }

  public static CommonResult<String> forbidden(String message) {
    return new CommonResult<String>(
      ResultCode.FORBIDDEN.getCode(), 
      ResultCode.FORBIDDEN.getMessage(), 
      message
    );
  }

  public T getResult() {
    return result;
  }

  public String getStatus() {
    return status;
  }

  public int getCode() {
    return code;
  }
}