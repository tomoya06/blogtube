package com.heavelop.blogtube.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.dao.DialogueDao;
import com.heavelop.blogtube.dto.CommonFetchPaginationResult;
import com.heavelop.blogtube.model.Bravo;
import com.heavelop.blogtube.model.Dialogue;
import com.heavelop.blogtube.model.DialogueFull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class DialogueService {
  @Autowired
  private DialogueDao dialogueDao;
  @Autowired
  private BravoService bravoService;
  
  public DialogueFull fetchRandom(Integer type) {
    val dialogue = dialogueDao.fetchRandom(type);
    this.appendDialogue(dialogue);
    return dialogue;
  }

  public List<DialogueFull> fetchRandomBatch(Integer count, Integer type) {
    return dialogueDao.fetchRandomBatch(count, type);
  }

  public CommonFetchPaginationResult<DialogueFull> fetchBatchByUser(Long creatorId, Integer count, Integer from) {
    creatorId = creatorId == null ? 0 : creatorId;
    count = count == null ? 10 : count;
    from = from == null ? 0 : from;
    val result = dialogueDao.fetchBatchByUser(creatorId, count, from);
    for (val dialogue: result) {
      this.appendDialogue(dialogue);
    }
    Integer total = dialogueDao.fetchCountByUser(creatorId);
    return new CommonFetchPaginationResult<DialogueFull>(total, count, from, result);
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

  private void appendDialogue(DialogueFull dialogueFull) {
    List<Bravo> bravos = bravoService.fetchBravosByDialogue(dialogueFull.getId());
    dialogueFull.setBravos(bravos);
  }
}