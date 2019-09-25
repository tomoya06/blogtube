package com.heavelop.blogtube.dao;

import java.util.List;

import com.heavelop.blogtube.model.Admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
  Admin findAdminByID(Integer id);
  List<Admin> findAdminByName(String name);
}