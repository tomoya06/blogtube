package com.heavelop.blogtube.dto;

import lombok.Data;

@Data
public class DialogueSubmitParam {
  private String content;
  private Integer type;
  private Long creatorId;
  private String creatorEmail;
}