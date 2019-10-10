package com.heavelop.blogtube.controller;

import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.common.api.CommonResult;
import com.heavelop.blogtube.dto.DialogueSubmitParam;
import com.heavelop.blogtube.model.Dialogue;
import com.heavelop.blogtube.service.DialogueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "LiveCodeController", description = "Live Code Operations")
@RestController
@RequestMapping("/dialogue")
public class DialogueController {
  @Autowired
  private DialogueService dialogueService;

  @GetMapping("/random")
  public CommonResult<Map<String, Object>> random() {
    return CommonResult.success(dialogueService.fetchRandom());
  }

  @GetMapping("/random/batch")
  public CommonResult<List<Dialogue>> randomBatch(@RequestParam Integer count) {
    return CommonResult.success(dialogueService.fetchRandomBatch(count));
  }

  @PostMapping(value = "/submit", consumes = "application/json")
  @ResponseBody
  public CommonResult<Object> submit(@RequestBody DialogueSubmitParam dialogueSubmitParam) {
    if (dialogueSubmitParam.getCreatorEmail() == null && dialogueSubmitParam.getCreatorId() == null) {
      return CommonResult.failed("Missing Creator Credit");
    }
    if (dialogueSubmitParam.getCreatorId() == null) {
      dialogueService.submit(
        dialogueSubmitParam.getContent(), 
        dialogueSubmitParam.getType()
      );
    } else {
      dialogueService.submit(
        dialogueSubmitParam.getContent(), 
        dialogueSubmitParam.getType(), 
        dialogueSubmitParam.getCreatorId()
      );
    }
    return CommonResult.success(true);
  }
}