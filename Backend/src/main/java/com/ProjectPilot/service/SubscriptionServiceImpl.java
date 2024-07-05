package com.ProjectPilot.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.SubscriptionRepository;
import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.entities.Subscription;
import com.ProjectPilot.entities.User;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  // @Autowired
  // private UserService userService;

  @Override
  public Subscription creareSubscription(User user) {
    Subscription subscription = new Subscription();
    subscription.setUser(user);
    subscription.setStartDate(LocalDate.now());
    subscription.setEndDate(LocalDate.now().plusMonths(12));
    subscription.setValid(true);
    subscription.setPlanType(PlanType.FREE);

    return subscriptionRepository.save(subscription);
  }

  @Override
  public Subscription getUsersSubscription(Long userId) throws Exception {
   Subscription subscription = subscriptionRepository.findByUserId(userId);
   if(!isValid(subscription)) {
    subscription.setPlanType(PlanType.FREE);
    subscription.setEndDate(LocalDate.now().plusMonths(12)); 
    subscription.setStartDate(LocalDate.now());
   }
   return subscriptionRepository.save(subscription);
  }

  @Override
  public Subscription upgradeSubscription(Long userId, PlanType type) {
    Subscription subscription = subscriptionRepository.findByUserId(userId);
    subscription.setPlanType(type);
    subscription.setStartDate(LocalDate.now());
    if(type.equals(PlanType.ANNUALLY)){
      subscription.setEndDate(LocalDate.now().plusMonths(12));
    }
    else subscription.setEndDate(LocalDate.now().plusMonths(1));
    return subscriptionRepository.save(subscription);
  }

  @Override
  public boolean isValid(Subscription subscription) {
    if(subscription.getPlanType().equals(PlanType.FREE))  return true;
    LocalDate end = subscription.getEndDate();
    LocalDate curr = LocalDate.now();
    return end.isAfter(curr) || end.isEqual(curr);
  }

}
