package com.heavelop.blogtube.service;

import java.util.ArrayList;
import java.util.List;

import com.heavelop.blogtube.common.api.UserRole;
import com.heavelop.blogtube.dao.AdminDao;
import com.heavelop.blogtube.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthDetailsService implements UserDetailsService {
  @Autowired
  private AdminDao adminDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminDao.findAdminByName(username);
    if (admin == null) {
      throw new UsernameNotFoundException("username not found");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(UserRole.getMessage(admin.getRole())));
    return new User(username, admin.getPassword(), authorities);
  }

}