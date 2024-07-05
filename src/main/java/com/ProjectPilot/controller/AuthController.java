package com.ProjectPilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.Repository.UserRepository;
import com.ProjectPilot.config.JwtProvider;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.LoginRequest;
import com.ProjectPilot.response.AuthResponse;
import com.ProjectPilot.service.CustomUserDetailsImpl;
import com.ProjectPilot.service.SubscriptionService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomUserDetailsImpl customUserDetailsImpl;

  @Autowired
  private SubscriptionService subscriptionService;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> CreateUserHandler(@RequestBody User user) throws Exception{
    User isUserExist = userRepository.findByEmail(user.getEmail());
    if(isUserExist != null) {
      throw new Exception("Email Already registred");
    }

    User createdUser = new User();
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
    createdUser.setEmail(user.getEmail());
    createdUser.setFullname(user.getFullname());

    User savedUser = userRepository.save(createdUser);

    subscriptionService.creareSubscription(savedUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();

    res.setMessage("Signup Success");
    res.setJwt(jwt);

    return new ResponseEntity<>(res,HttpStatus.CREATED);
    
  }


  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> Signin(@RequestBody LoginRequest loginRequest){
    String username = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Authentication authentication = authenticate(username,password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();

    res.setMessage("SignIn Success");
    res.setJwt(jwt);

    return new ResponseEntity<>(res,HttpStatus.CREATED);
  }


  private Authentication authenticate(String username, String password) {
    UserDetails userDetails = customUserDetailsImpl.loadUserByUsername(username);
    if(userDetails == null){
      throw new BadCredentialsException("Invalid Username");
    }
    if(!passwordEncoder.matches(password, userDetails.getPassword())){
      throw new BadCredentialsException("Invalid Password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails,null);
  }
}
