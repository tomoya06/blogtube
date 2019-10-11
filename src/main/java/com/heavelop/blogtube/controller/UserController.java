package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.common.api.ResultCode;
import com.heavelop.blogtube.dto.UserLoginParam;
import com.heavelop.blogtube.dto.UserRegisterParam;
import com.heavelop.blogtube.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "UserController", description = "User Operations")
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  @PostMapping(value = "/register", consumes = "application/json")
  public CommonResult<String> register(@RequestBody UserRegisterParam userRegisterParam) {
    if (!RegExp.username.matches(userRegisterParam.getUsername())) {
      return CommonResult.bad("username");
    }
    if (!RegExp.password.matches(userRegisterParam.getPassword())) {
      return CommonResult.bad("password");
    }
    if (!RegExp.email.matches(userRegisterParam.getEmail())) {
      return CommonResult.bad("email");
    }

    try {
      userService.registerUser(
        userRegisterParam.getUsername(), 
        userRegisterParam.getPassword(),
        userRegisterParam.getEmail()
      );
    } catch (Exception e) {
      return CommonResult.failed(ResultCode.FAILED.getMessage());
    }

    return CommonResult.success(ResultCode.SUCCESS.getMessage());
  }

  @PostMapping(value = "/login", consumes = "application/json")
  public CommonResult<String> login(@RequestBody UserLoginParam userLoginParam) {
    String username = userLoginParam.getUsername();
    String password = userLoginParam.getPassword();
    try {
      String result;
      result = userService.login(username, password);
      return CommonResult.success(result);
    } catch (Exception e) {
      return CommonResult.bad("Login Failed");
    }
  }
}