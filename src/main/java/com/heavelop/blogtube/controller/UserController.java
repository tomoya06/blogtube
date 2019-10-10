package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
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
  
  @PostMapping(value = "/register", consumes = "application/form")
  public CommonResult<Boolean> register(@RequestBody UserRegisterParam userRegisterParam) {
    userService.registerUser(userRegisterParam);
    return CommonResult.success(true);
  }

  @PostMapping(value = "/login", consumes = "application/form")
  public CommonResult<String> login(@RequestBody UserLoginParam userLoginParam) {
    return CommonResult.success("GOOD");
  }
}