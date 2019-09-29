package com.heavelop.blogtube.common.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
  @Value("${jwt.claim.username}")
  private String CLAIM_KEY_USERNAME;
  @Value("${jwt.claim.created}")
  private String CLAIM_KEY_CREATED;
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Long expiration;
  
  private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
  
  public String getUserNameFromToken(String token) {
    Claims claims = getClaimsFromToken(token);
    String username = claims.getSubject();
    return username;
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    String username = getUserNameFromToken(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  private boolean isTokenExpired(String token) {
    Claims claims = getClaimsFromToken(token);
    Date expiration = claims.getExpiration();
    return expiration.before(new Date());
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
      .setSigningKey(key)
      .parseClaimsJws(token)
      .getBody();
  }
  
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
      .setClaims(claims)
      .setExpiration(generateExpirationDate())
      .signWith(key)
      .compact();
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }
}