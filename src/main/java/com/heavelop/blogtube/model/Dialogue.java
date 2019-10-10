package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class Dialogue {
  private Long msgId;
  private String content;
  private Long createTime;
  private Integer type;
  private Long creatorId;
  private Integer visit;
  private Boolean isBanned;
}