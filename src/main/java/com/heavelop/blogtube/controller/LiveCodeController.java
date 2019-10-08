package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.service.EmailService;
import com.heavelop.blogtube.service.LiveCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "LiveCodeController", description = "Live Code Operations")
@RestController
@RequestMapping("/code")
public class LiveCodeController {
  @Autowired
  private LiveCodeService liveCodeService;
  @Autowired
  private EmailService emailService;

  @Value("${mail.liveCode.subject}")
  private String emailSubject;
  @Value("${mail.liveCode.content.prefix}")
  private String emailContentPrefix;
  @Value("${mail.liveCode.content.suffix}")
  private String emailContentSuffix;

  @GetMapping("/new")
  public CommonResult<Boolean> generateAndSendCode(@RequestParam String email) {
    String newCode = liveCodeService.generateCode(email);
    String emailContent = emailContentPrefix + newCode + emailContentSuffix;
    emailService.sendTo(email, emailSubject, emailContent);
    return CommonResult.success(true);
  }

  @GetMapping("/validate")
  public CommonResult<Boolean> validateCode(@RequestParam String email, @RequestParam String code) {
    return CommonResult.success(liveCodeService.validateCode(email, code));
  }
}