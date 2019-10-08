package com.heavelop.blogtube.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.heavelop.blogtube.common.api.UserRole;
import com.heavelop.blogtube.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserAuthDetails implements UserDetails {
  private static final long serialVersionUID = -4095235947092839904L;
  private User user;
  private List<SimpleGrantedAuthority> permissionList;

  public UserAuthDetails(User user) {
    this.user = user;
    this.permissionList = new ArrayList<>();
    permissionList.add(new SimpleGrantedAuthority(UserRole.getMessage(user.getRole())));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return permissionList;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }
}