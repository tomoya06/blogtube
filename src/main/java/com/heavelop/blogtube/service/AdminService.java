package com.heavelop.blogtube.service;

import com.heavelop.blogtube.common.util.JwtTokenUtil;
import com.heavelop.blogtube.dao.AdminDao;
import com.heavelop.blogtube.dto.AdminAuthDetails;
import com.heavelop.blogtube.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
  @Autowired
  private AdminAuthDetailsService userDetailsService;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private AdminDao adminDao;

  public Admin findAdminByID(Integer id) {
    return adminDao.findAdminByID(id);
  }

  public Admin findAdminByName(String name) {
    return adminDao.findAdminByName(name);
  }

  public String login(String username, String password) {
    String token = null;
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (!userDetails.getPassword().equals(password)) {
      throw new BadCredentialsException("invalid password");
    }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    token = jwtTokenUtil.generateToken(userDetails);
    return token;
  }
}