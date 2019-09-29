package com.heavelop.blogtube.dao;

import com.heavelop.blogtube.model.Admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
  Admin findAdminByID(Integer id);
  Admin findAdminByName(String username);
  void registerAdmin(String username, String password);
  void resetPassword(String username, String oldPassword, String newPassword);
}