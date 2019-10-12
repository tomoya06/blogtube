package com.heavelop.blogtube.dao;

import java.util.List;

import com.heavelop.blogtube.model.Bravo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BravoDao {
  List<Bravo> fetchBravosByDialogue(Long id);
  void addBravo(Long creatorId, String creatorEmail, String creatorIP, String content, Long targetId, Long createTime);
}