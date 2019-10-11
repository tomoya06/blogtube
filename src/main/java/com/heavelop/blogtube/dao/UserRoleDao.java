package com.heavelop.blogtube.dao;

import com.heavelop.blogtube.model.UserRole;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {
  UserRole findUserRole(Integer id);
}