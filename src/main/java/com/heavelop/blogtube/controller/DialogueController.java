package com.heavelop.blogtube.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.dto.CommonFetchPaginationResult;
import com.heavelop.blogtube.dto.DialogueBravoParam;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import com.heavelop.blogtube.model.Dialogue;
import com.heavelop.blogtube.model.DialogueFull;
import com.heavelop.blogtube.service.BravoService;
import com.heavelop.blogtube.service.DialogueService;
import com.vdurmont.emoji.EmojiManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Dialogue for Public", description = "Dialogue Controller without Login")
@RestController
@RequestMapping("/dialogue/public")
public class DialogueController {
  @Autowired
  private DialogueService dialogueService;
  @Autowired
  private BravoService bravoService;

  @GetMapping("/random")
  public CommonResult<DialogueFull> random(
    @RequestParam(required = false) Integer type
  ) {
    return CommonResult.success(dialogueService.fetchRandom(type));
  }

  @GetMapping("/random/batch")
  public CommonResult<List<DialogueFull>> randomBatch(
    @RequestParam Integer count,
    @RequestParam(required = false) Integer type
  ) {
    return CommonResult.success(dialogueService.fetchRandomBatch(count, type));
  }

  @GetMapping("/fetch/user")
  public CommonResult<CommonFetchPaginationResult<DialogueFull>> fetchBatchByUser(
    @RequestParam(required = false) Long id,
    @RequestParam(required = false) Integer size,
    @RequestParam(required = false) Integer from
  ) {
    return CommonResult.success(dialogueService.fetchBatchByUser(id, size, from));
  }

  @PostMapping(value = "/submit", consumes = "application/json")
  @ResponseBody
  public CommonResult<Object> publicSubmit(@RequestBody DialogueSubmitParam dialogueSubmitParam) {
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

  @PostMapping(value = "/bravo", consumes = "application/json")
  @ResponseBody
  public CommonResult<Object> publicBravo(@RequestBody DialogueBravoParam param, HttpServletRequest request) {
    String content = param.getContent();
    if (!EmojiManager.isEmoji(content)) {
      return CommonResult.bad("emoji");
    }
    String creatorIP = request.getRemoteAddr();
    Long targetId = param.getTargetId();
    bravoService.addBravo(null, null, creatorIP, content, targetId);
    return CommonResult.success(true);
  } 
}