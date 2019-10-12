package com.heavelop.blogtube.controller;

import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.dto.CommonFetchPaginationResult;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import com.heavelop.blogtube.model.Dialogue;
import com.heavelop.blogtube.service.DialogueService;

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

  @GetMapping("/random")
  public CommonResult<Map<String, Object>> random(
    @RequestParam(required = false) Integer type
  ) {
    return CommonResult.success(dialogueService.fetchRandom(type));
  }

  @GetMapping("/random/batch")
  public CommonResult<List<Dialogue>> randomBatch(
    @RequestParam Integer count,
    @RequestParam(required = false) Integer type
  ) {
    return CommonResult.success(dialogueService.fetchRandomBatch(count, type));
  }

  @GetMapping("/fetch/user")
  public CommonResult<CommonFetchPaginationResult<Dialogue>> fetchBatchByUser(
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
}