package com.heavelop.blogtube.dto;

public class DialogueSubmitParam {
  private String content;
  private Integer type;
  private Long creatorId;
  private String creatorEmail;

  public String getContent() {
    return this.content;
  }

  public String getCreatorEmail() {
    return creatorEmail;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getType() {
    return this.type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getCreatorId() {
    return this.creatorId;
  }
  
  public void setCreatorEmail(String creatorEmail) {
    this.creatorEmail = creatorEmail;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

}