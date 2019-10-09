package com.heavelop.blogtube.model;

import lombok.Data;

@Data
public class Dialogue {
  private Integer msgId;
  private String content;
  private Integer createTime;
  private Integer type;
  private Integer creatorId;
  private Integer visit;
  private Boolean isBanned;
}