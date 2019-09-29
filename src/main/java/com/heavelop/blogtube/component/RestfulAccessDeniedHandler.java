package com.heavelop.blogtube.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heavelop.blogtube.common.api.CommonResult;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;

@Component
public class RestfulAccessDeniedHandler extends AccessDeniedHandlerImpl {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
      throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(exception.getMessage())));
    response.getWriter().flush();
  }
}