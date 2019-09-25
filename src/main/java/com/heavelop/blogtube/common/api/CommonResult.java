package com.heavelop.blogtube.common.api;

public class CommonResult<T> {
  private int code;
  private String status;
  private T Data;

  public CommonResult(Integer code, String status, T Data) {
    this.code = code;
    this.status = status;
    this.Data = Data;
  }

  public static <T> CommonResult<T> success(T data) {
    return new CommonResult<T>(
      ResultCode.SUCCESS.getCode(),
      ResultCode.SUCCESS.getMessage(),
      data
    );
  }

  public static <T> CommonResult<T> failed(T data) {
    return new CommonResult<T>(
      ResultCode.FAILED.getCode(),
      ResultCode.FAILED.getMessage(),
      data
    );
  }

  public T getData() {
    return Data;
  }

  public String getStatus() {
    return status;
  }

  public int getCode() {
    return code;
  }
}