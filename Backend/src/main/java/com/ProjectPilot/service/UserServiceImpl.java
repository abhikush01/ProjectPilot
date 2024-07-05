package com.ProjectPilot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.UserRepository;
import com.ProjectPilot.config.JwtProvider;
import com.ProjectPilot.entities.User;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public User findUserProfileByJwt(String jwt) throws Exception {
    jwt = jwt.substring(7);
    String email = JwtProvider.getEmailFromToken(jwt);
    return findUserByEmail(email);
  }

  @Override
  public User findUserByEmail(String email) throws Exception {
    User user = userRepository.findByEmail(email);
    if(user == null) throw new Exception("User not Exist");
    return user;
  }

  @Override
  public User findUserById(Long userId) throws Exception {
    Optional<User> user= userRepository.findById(userId);
    if(user.isEmpty()) throw new Exception("User not Exist");
    return user.get();
  }

  @Override
  public User updateUserProjectSize(User user, int number) {
   user.setProjectSize(user.getProjectSize()+number);
   return userRepository.save(user);
  }

}
