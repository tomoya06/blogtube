package com.heavelop.blogtube.config;

import com.heavelop.blogtube.bo.UserAuthDetails;
import com.heavelop.blogtube.common.api.ResultCode;
import com.heavelop.blogtube.component.JwtAuthenticationTokenFilter;
import com.heavelop.blogtube.component.RestfulAccessDeniedHandler;
import com.heavelop.blogtube.component.RestfulAuthenticationEntryPoint;
import com.heavelop.blogtube.model.User;
import com.heavelop.blogtube.service.UserService;

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
  private UserService userService;
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
        "/img/*",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js",
        "/swagger-resources/**",
        "/v2/api-docs/**"
      )
      .permitAll()
      .antMatchers("/user/login", "/user/register")
      .permitAll()
      .antMatchers("/code/**")
      .permitAll()
      .antMatchers("/dialogue/public/**")
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
      User user = userService.findUserByName(username);
      if (user != null) {
        return new UserAuthDetails(user);
      }
      throw new UsernameNotFoundException(ResultCode.NOTFOUND.getMessage());
    };
  }
}