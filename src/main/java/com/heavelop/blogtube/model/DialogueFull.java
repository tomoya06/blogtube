package com.heavelop.blogtube.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DialogueFull {
  private Long id;
  private Long msgId;
  private String content;
  private Long createTime;
  @JsonIgnore
  private Integer type;
  private DialogueType typeDetail;
  @JsonIgnore
  private Long creatorId;
  @JsonIgnore
  private String creatorEmail;
  private User creator;
  private Integer visit;
  private Boolean isBanned;
  private List<Bravo> bravos;
}