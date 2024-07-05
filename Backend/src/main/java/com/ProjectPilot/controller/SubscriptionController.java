package com.ProjectPilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.entities.Subscription;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.service.SubscriptionService;
import com.ProjectPilot.service.UserService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private UserService userService;


  @GetMapping("/user")
  public ResponseEntity<Subscription> getUserSubscription(
    @RequestHeader("Authorization") String jwt
  ) throws Exception{
    User user = userService.findUserProfileByJwt(jwt);
    Subscription subscription = subscriptionService.getUsersSubscription(user.getId());
    return ResponseEntity.ok(subscription);
  }

  @PatchMapping("/upgrade")
  public ResponseEntity<Subscription> upgradeUserSubscription(
    @RequestHeader("Authorization") String jwt,
    @RequestParam PlanType planType
  ) throws Exception{
    User user = userService.findUserProfileByJwt(jwt);
    Subscription subscription = subscriptionService.upgradeSubscription(user.getId(),planType);
    return ResponseEntity.ok(subscription);
  }

}
