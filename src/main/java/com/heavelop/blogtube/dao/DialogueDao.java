package com.heavelop.blogtube.dao;

import java.util.List;
import java.util.Map;

import com.heavelop.blogtube.model.Dialogue;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DialogueDao {
  Map<String, Object> fetchRandom();
  List<Dialogue> fetchRandomBatch(Integer count);
  void submit(String content, Integer type, Long createTime, Long creatorId, String creatorEmail);
}