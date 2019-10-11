package com.heavelop.blogtube.common.api;

import java.util.regex.Pattern;

public enum RegExp {
  username("^[\\w]{6,20}$"),
  password("^[\\w-]{6,30}$"),
  email("^\\w+[@]{1}\\w+[.]\\w+$");

  private String expression;

  private RegExp(String expression) {
    this.expression = expression;
  }

  public String getExpression() {
    return expression;
  }

  public Boolean matches(String input) {
    return Pattern.matches(this.expression, input);
  }
}