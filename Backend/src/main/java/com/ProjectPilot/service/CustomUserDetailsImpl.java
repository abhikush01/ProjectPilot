package com.ProjectPilot.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.UserRepository;
import com.ProjectPilot.entities.User;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   User user = userRepository.findByEmail(username);
   if(user == null){
    throw new UsernameNotFoundException("User not Found"+username);
   }
    List<GrantedAuthority> authorities = new ArrayList<>();
    return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
  }

}
