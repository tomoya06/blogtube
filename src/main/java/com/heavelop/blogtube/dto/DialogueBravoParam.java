package com.heavelop.blogtube.dto;

import lombok.Data;

@Data
public class DialogueBravoParam {
  Long creatorId;
  String creatorEmail;
  String content;
  Long targetId;
}