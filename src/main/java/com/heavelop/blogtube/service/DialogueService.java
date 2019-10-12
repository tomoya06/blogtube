package com.heavelop.blogtube.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.dao.DialogueDao;
import com.heavelop.blogtube.dto.CommonFetchPaginationResult;
import com.heavelop.blogtube.model.Dialogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class DialogueService {
  @Autowired
  private DialogueDao dialogueDao;
  
  public Map<String, Object> fetchRandom(Integer type) {
    return dialogueDao.fetchRandom(type);
  }

  public List<Dialogue> fetchRandomBatch(Integer count, Integer type) {
    return dialogueDao.fetchRandomBatch(count, type);
  }

  public CommonFetchPaginationResult<Dialogue> fetchBatchByUser(Long creatorId, Integer count, Integer from) {
    creatorId = creatorId == null ? 0 : creatorId;
    count = count == null ? 10 : count;
    from = from == null ? 0 : from;
    val result = dialogueDao.fetchBatchByUser(creatorId, count, from);
    Integer total = dialogueDao.fetchCountByUser(creatorId);
    return new CommonFetchPaginationResult<Dialogue>(total, count, from, result);
  }

  public void registeredUserSubmit(String content, Integer type, Long creatorId) {
    this.submit(content, type, creatorId, null);
  }

  public void publicSubmit(String content, Integer type, String creatorEmail) {
    this.submit(content, type, -1l, creatorEmail);
  }
  
  private void submit(String content, Integer type, Long creatorId, String creatorEmail) {
    Long createTime = new Date().getTime();
    dialogueDao.submit(content, type, createTime, creatorId, creatorEmail);
  }
}