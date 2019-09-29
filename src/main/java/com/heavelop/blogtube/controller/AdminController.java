package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.dto.UserLoginParam;
import com.heavelop.blogtube.model.Admin;
import com.heavelop.blogtube.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private AdminService adminService;

  @RequestMapping("/test")
  public CommonResult<Admin> testQuery() {
    return CommonResult.success(adminService.findAdminByID(1));    
  }

  @RequestMapping("/name")
  public CommonResult<Admin> findAdminByName(@RequestParam(value = "name") String name) {
    return CommonResult.success(adminService.findAdminByName(name));
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
  public CommonResult<String> login(@RequestBody UserLoginParam userLoginParam) {
    String result = adminService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
    return CommonResult.success(result);
  }
}