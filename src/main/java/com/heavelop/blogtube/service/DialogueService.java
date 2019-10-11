package com.heavelop.blogtube.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.dao.DialogueDao;
import com.heavelop.blogtube.model.Dialogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogueService {
  @Autowired
  private DialogueDao dialogueDao;
  
  public Map<String, Object> fetchRandom() {
    return dialogueDao.fetchRandom();
  }

  public List<Dialogue> fetchRandomBatch(Integer count) {
    return dialogueDao.fetchRandomBatch(count);
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