package com.heavelop.blogtube.service;

import java.util.Date;

import com.heavelop.blogtube.bo.UserAuthDetails;
import com.heavelop.blogtube.common.api.RegExp;
import com.heavelop.blogtube.common.util.JwtTokenUtil;
import com.heavelop.blogtube.dao.UserDao;
import com.heavelop.blogtube.dao.UserRoleDao;
import com.heavelop.blogtube.model.User;
import com.heavelop.blogtube.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserDao userDao;
  @Autowired
  private UserRoleDao userRoleDao;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  private void injectUserRolename(User user) {
    if (user != null) {
      UserRole userRole = this.findUserRole(user.getRole());
      user.setRolename(userRole.getRolename());
    }
  }

  public User findUserByName(String username) {
    User targetUser = userDao.findUserByName(username);
    injectUserRolename(targetUser);
    return targetUser;
  }

  public User findUserByEmail(String email) {
    User targetUser = userDao.findUserByEmail(email);
    injectUserRolename(targetUser);
    return targetUser;
  }
  
  private UserRole findUserRole(Integer id) {
    return userRoleDao.findUserRole(id);
  }

  public void registerUser(String username, String password, String email) {
    Long createTime = (new Date()).getTime();
    userDao.registerUser(username, password, email, createTime);
  }
  
  public String login(String username, String password) {
    String token = null;
    User targetUser;
    // SUPPORT USERNAME OR EMAIL
    if (RegExp.email.matches(username)) {
      targetUser = this.findUserByEmail(username);
    } else {
      targetUser = this.findUserByName(username);
    }
    if (targetUser == null) {
      throw new BadCredentialsException("");
    }
    UserDetails userDetails = new UserAuthDetails(targetUser);
    if (!userDetails.getPassword().equals(password)) {
      throw new BadCredentialsException("");
    }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    token = jwtTokenUtil.generateToken(userDetails);
    return token;
  }
}