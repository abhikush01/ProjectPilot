package com.ProjectPilot.service;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.entities.Subscription;
import com.ProjectPilot.entities.User;

public interface SubscriptionService {

  Subscription creareSubscription(User user);

  Subscription getUsersSubscription(Long userId) throws Exception;

  Subscription upgradeSubscription(Long userId,PlanType type);

  boolean isValid (Subscription subscription);

}
