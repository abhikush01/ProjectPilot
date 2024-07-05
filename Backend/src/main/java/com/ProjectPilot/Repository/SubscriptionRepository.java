package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

  Subscription findByUserId(Long userId);

}
