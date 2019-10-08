package com.heavelop.blogtube.service;

import com.heavelop.blogtube.bo.UserAuthDetails;
import com.heavelop.blogtube.common.util.JwtTokenUtil;
import com.heavelop.blogtube.dao.UserDao;
import com.heavelop.blogtube.dto.UserRegisterParam;
import com.heavelop.blogtube.model.User;

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
  private JwtTokenUtil jwtTokenUtil;

  public User findUserByName(String username) {
    return userDao.findUserByName(username);
  }

  public void registerUser(UserRegisterParam userRegisterParam) {
    userDao.registerUser(userRegisterParam.getUsername(), userRegisterParam.getPassword());
  }

  public String login(String username, String password) {
    String token = null;
    UserDetails userDetails = new UserAuthDetails(this.findUserByName(username));
    if (!userDetails.getPassword().equals(password)) {
      throw new BadCredentialsException("invalid password");
    }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    token = jwtTokenUtil.generateToken(userDetails);
    return token;
  }
}