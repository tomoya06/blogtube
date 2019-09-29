package com.heavelop.blogtube.component;

import com.heavelop.blogtube.common.api.CommonResult;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
  @ExceptionHandler
  public CommonResult<String> commonExceptionHandler(DuplicateKeyException exception) {
    return CommonResult.failed("主键重复");
  }
}