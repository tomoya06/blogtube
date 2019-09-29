package com.heavelop.blogtube.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heavelop.blogtube.common.api.CommonResult;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import cn.hutool.json.JSONUtil;

public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(authException.getMessage())));
    response.getWriter().flush();
  }

}