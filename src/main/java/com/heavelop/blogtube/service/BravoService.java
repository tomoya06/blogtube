package com.heavelop.blogtube.service;

import java.util.Date;
import java.util.List;

import com.heavelop.blogtube.dao.BravoDao;
import com.heavelop.blogtube.model.Bravo;
import com.heavelop.blogtube.model.BravoType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BravoService {
  @Autowired
  private BravoDao bravoDao;

  public List<Bravo> fetchBravosByDialogue(Long id) {
    return bravoDao.fetchBravosByDialogue(id);
  }

  public void addBravo(Long creatorId, String creatorEmail, String creatorIP, String content, Long targetId) {
    Long createTime = (new Date()).getTime();
    if (creatorId == null) {
      bravoDao.addBravo(-1l, creatorEmail, creatorIP, content, targetId, createTime);
    } else {
      bravoDao.addBravo(creatorId, creatorEmail, creatorIP, content, targetId, createTime);
    }
  }

  public List<BravoType> supportedBravos() {
    return bravoDao.supportedBravos();
  }
}