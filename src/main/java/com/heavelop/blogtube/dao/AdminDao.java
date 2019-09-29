package com.heavelop.blogtube.dao;

import com.heavelop.blogtube.model.Admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
  Admin findAdminByID(Integer id);
  Admin findAdminByName(String name);
}