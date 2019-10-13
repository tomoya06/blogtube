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
  private LiveCodeService liveCodeService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  private void injectUserRolename(User user) {
    if (user != null) {
      UserRole userRole = this.findUserRole(user.getRole());
      user.setRolename(userRole.getRolename());
    }
  }

  public User getAdminUser() {
    User admin = new User();
    admin.setUsername("admin");
    return admin;
  }

  public User findUserById(Long id) {
    User targetUser = userDao.findUserById(id);
    return targetUser;
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

  private String generateToken(UserDetails userDetails) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    String token = jwtTokenUtil.generateToken(userDetails);
    return token;
  }

  public void registerUser(String username, String password, String email) {
    Long createTime = (new Date()).getTime();
    userDao.registerUser(username, password, email, createTime);
  }

  /**
   * Login With Live Code
   * @param email
   * @param code
   * @return
   */
  public String loginWithEmailAndLiveCode(String email, String code) {
    Boolean validateResult = liveCodeService.validateCode(email, code);
    if (!validateResult) {
      throw new BadCredentialsException("");
    }
    User targetUser = this.findUserByEmail(email);
    UserDetails userDetails = new UserAuthDetails(targetUser);
    return this.generateToken(userDetails);
  }
  
  /**
   * Login With Username or Email
   * @param username
   * @param password
   * @return
   */
  public String login(String username, String password) {
    User targetUser;
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
    return this.generateToken(userDetails);
  }
}