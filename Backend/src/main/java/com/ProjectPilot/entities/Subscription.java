package com.ProjectPilot.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private LocalDate startDate;
  private LocalDate endDate;

  private PlanType planType;

  private boolean isValid;

  @OneToOne
  private User user;

}
