package com.heavelop.blogtube.dao;

import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.model.Dialogue;
import com.heavelop.blogtube.model.DialogueFull;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DialogueDao {
  DialogueFull fetchRandom(Integer type);
  List<DialogueFull> fetchRandomBatch(Integer count, Integer type);
  List<DialogueFull> fetchBatchByUser(Long creatorId, Integer count, Integer from);
  Integer fetchCountByUser(Long creatorId);
  void submit(String content, Integer type, Long createTime, Long creatorId, String creatorEmail);
}