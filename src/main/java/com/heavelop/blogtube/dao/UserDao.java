package com.heavelop.blogtube.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
  public void registerUser(String username, String password);
}