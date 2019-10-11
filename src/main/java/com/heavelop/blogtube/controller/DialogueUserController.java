package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Dialogue for Logined User")
@RestController
@RequestMapping("/dialogue/user")
public class DialogueUserController {
  @PostMapping(value = "/submit", consumes = "application/json")
  public CommonResult<Object> userSubmit(@RequestBody DialogueSubmitParam dialogueSubmitParam) {
    return CommonResult.success(true);
  }
}