package com.heavelop.blogtube.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.heavelop.blogtube.common.api.UserRole;
import com.heavelop.blogtube.model.Admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AdminAuthDetails implements UserDetails {
  private static final long serialVersionUID = -4095235947092839904L;
  private Admin admin;
  private List<SimpleGrantedAuthority> permissionList;

  public AdminAuthDetails(Admin admin) {
    this.admin = admin;
    this.permissionList = new ArrayList<>();
    permissionList.add(new SimpleGrantedAuthority(UserRole.getMessage(admin.getRole())));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return permissionList;
  }

  @Override
  public String getPassword() {
    return this.admin.getPassword();
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
    return this.admin.getUsername();
  }
}