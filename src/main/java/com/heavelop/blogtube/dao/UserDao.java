package com.heavelop.blogtube.dao;

import com.heavelop.blogtube.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
  void registerUser(String username, String password, String email, Long createTime);
  User findUserByName(String username);
  User findUserByEmail(String email);
}