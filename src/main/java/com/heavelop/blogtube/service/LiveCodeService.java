package com.heavelop.blogtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.RandomUtil;

@Service
public class LiveCodeService {  
  @Value("${redis.key.prefix.liveCode}")
  private String LIVECODE_REDIS_KEY_PREFIX;
  @Value("${redis.key.expire.liveCode}")
  private Long LIVECODE_REDIS_EXPIRE;
  @Autowired
  private RedisService redisService;
  
  public String generateCode(String email) {
    Integer newCode = RandomUtil.randomInt(100000, 999999);
    redisService.set(LIVECODE_REDIS_KEY_PREFIX + email, newCode.toString());
    redisService.expire(LIVECODE_REDIS_KEY_PREFIX + email, LIVECODE_REDIS_EXPIRE);
    return newCode.toString();
  }

  public Boolean validateCode(String email, String code) {
    try {
      String savedCode = redisService.get(LIVECODE_REDIS_KEY_PREFIX + email);
      return savedCode.equals(code);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}