package com.heavelop.blogtube.service;

import java.util.List;

import com.heavelop.blogtube.dao.AdminDao;
import com.heavelop.blogtube.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
  @Autowired
  private AdminDao adminDao;

  public Admin findAdminByID(Integer id) {
    return adminDao.findAdminByID(id);
  }

  public List<Admin> findAdminByName(String name) {
    return adminDao.findAdminByName(name);
  }
} 