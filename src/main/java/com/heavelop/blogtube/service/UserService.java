package com.heavelop.blogtube.service;

import com.heavelop.blogtube.dao.UserDao;
import com.heavelop.blogtube.dto.UserRegisterParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserDao userDao;

  public void registerUser(UserRegisterParam userRegisterParam) {
    userDao.registerUser(userRegisterParam.getUsername(), userRegisterParam.getPassword());
  }
}