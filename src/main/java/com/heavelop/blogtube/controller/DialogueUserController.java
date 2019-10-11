package com.heavelop.blogtube.controller;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import com.heavelop.blogtube.service.DialogueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Dialogue for Logined User")
@RestController
@RequestMapping("/dialogue/user")
public class DialogueUserController {
  @Autowired
  private DialogueService dialogueService;

  @PostMapping(value = "/submit", consumes = "application/json")
  public CommonResult<Object> userSubmit(@RequestBody DialogueSubmitParam dialogueSubmitParam) {
    if (!RegExp.email.matches(dialogueSubmitParam.getCreatorEmail())) {
      return CommonResult.bad("email");
    }
    dialogueService.publicSubmit(
      dialogueSubmitParam.getContent(),
      dialogueSubmitParam.getType(),
      dialogueSubmitParam.getCreatorEmail()
    );
    return CommonResult.success(true);
  }
}