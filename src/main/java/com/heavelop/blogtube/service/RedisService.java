package com.heavelop.blogtube.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
  @Autowired
  private StringRedisTemplate template;
  
  public void set(String key, String value) {
    template.opsForValue().set(key, value);
  }  

  public String get(String key) {
    return template.opsForValue().get(key);
  }

  public Boolean expire(String key, long timeout) {
    return template.expire(key, timeout, TimeUnit.SECONDS);
  }
}