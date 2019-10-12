package com.heavelop.blogtube.config;

import javax.servlet.http.HttpServletRequest;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.ResultCode;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandlerConfig {
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public CommonResult<String> MissingParamHandler(HttpServletRequest request, Exception e) {
    return CommonResult.bad(e.getMessage());
  }

  @ExceptionHandler(BadSqlGrammarException.class)
  @ResponseBody
  public CommonResult<String> BadSqlGrammarHandler(HttpServletRequest request, Exception e) {
    e.printStackTrace();
    return CommonResult.failed(ResultCode.FAILED.getMessage());
  }
}