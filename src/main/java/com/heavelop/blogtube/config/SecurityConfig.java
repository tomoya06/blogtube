package com.heavelop.blogtube.config;

import com.heavelop.blogtube.common.api.ResultCode;
import com.heavelop.blogtube.component.JwtAuthenticationTokenFilter;
import com.heavelop.blogtube.component.RestfulAccessDeniedHandler;
import com.heavelop.blogtube.component.RestfulAuthenticationEntryPoint;
import com.heavelop.blogtube.bo.AdminAuthDetails;
import com.heavelop.blogtube.model.Admin;
import com.heavelop.blogtube.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AdminService adminService;
  @Autowired
  private RestfulAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired
  private RestfulAccessDeniedHandler accessDeniedHandler;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf() 
      .disable()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers(
        HttpMethod.GET,
        "/",
        "/*.html",
        "/favicon.ico",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js"
      )
      .permitAll()
      .antMatchers("/admin/login", "/admin/register")
      .permitAll()
      .antMatchers("/user/login", "/user/register")
      .permitAll()
      .anyRequest()
      .authenticated();
    
      httpSecurity.headers().cacheControl();
      httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
      httpSecurity.exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService())
      .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      Admin admin = adminService.findAdminByName(username);
      if (admin != null) {
        return new AdminAuthDetails(admin);
      }
      throw new UsernameNotFoundException(ResultCode.NOTFOUND.getMessage());
    };
  }
}