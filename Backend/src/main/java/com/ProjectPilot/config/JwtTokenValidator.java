package com.ProjectPilot.config;


import javax.crypto.SecretKey;

import java.util.*;

import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter{

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
    try{
      String jwt = request.getHeader(JwtConstents.JWT_HEADER);
    if(jwt != null){
      jwt = jwt.substring(7);
      try{
        SecretKey keys = Keys.hmacShaKeyFor(JwtConstents.SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(keys).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));
        String authorities = String.valueOf(claims.get("authorities"));

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null ,auths);

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      
    }
    filterChain.doFilter(request, response);
    
    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }


     

}
