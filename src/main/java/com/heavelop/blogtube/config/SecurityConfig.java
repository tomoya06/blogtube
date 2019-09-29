package com.heavelop.blogtube.config;

import com.heavelop.blogtube.component.JwtAuthenticationTokenFilter;
import com.heavelop.blogtube.component.RestfulAccessDeniedHandler;
import com.heavelop.blogtube.component.RestfulAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

  
}