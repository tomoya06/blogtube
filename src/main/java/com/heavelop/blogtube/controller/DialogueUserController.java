package com.heavelop.blogtube.controller;

import javax.servlet.http.HttpServletRequest;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.dto.DialogueBravoParam;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import com.heavelop.blogtube.model.User;
import com.heavelop.blogtube.service.BravoService;
import com.heavelop.blogtube.service.DialogueService;
import com.heavelop.blogtube.service.UserService;
import com.vdurmont.emoji.EmojiManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Dialogue for Logined User")
@RestController
@RequestMapping("/dialogue/user")
public class DialogueUserController {
  @Autowired
  private UserService userService;
  @Autowired
  private BravoService bravoService;

  @PostMapping(value = "/bravo", consumes = "application/json")
  @ResponseBody
  public CommonResult<Object> publicBravo(@RequestBody DialogueBravoParam param, HttpServletRequest request) {
    String content = param.getContent();
    if (!EmojiManager.isEmoji(content)) {
      return CommonResult.bad("emoji");
    }
    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user = userService.findUserByName(username);
    
    Long userId = user.getId();
    Long targetId = param.getTargetId();
    bravoService.addBravo(userId, null, null, content, targetId);
    return CommonResult.success(true);
  }

  // @PostMapping(value = "/submit", consumes = "application/json")
  // public CommonResult<Object> userSubmit(@RequestBody DialogueSubmitParam dialogueSubmitParam) {
  //   if (!RegExp.email.matches(dialogueSubmitParam.getCreatorEmail())) {
  //     return CommonResult.bad("email");
  //   }
  //   dialogueService.publicSubmit(
  //     dialogueSubmitParam.getContent(),
  //     dialogueSubmitParam.getType(),
  //     dialogueSubmitParam.getCreatorEmail()
  //   );
  //   return CommonResult.success(true);
  // }
}