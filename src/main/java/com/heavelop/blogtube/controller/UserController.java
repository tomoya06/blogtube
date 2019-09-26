package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.dto.UserRegisterParam;
import com.heavelop.blogtube.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
  @ResponseBody
  public CommonResult<Boolean> registerUser(@RequestBody UserRegisterParam userRegisterParam) {
    userService.registerUser(userRegisterParam);
    return CommonResult.success(true);
  }
}