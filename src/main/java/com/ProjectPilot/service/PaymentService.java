package com.ProjectPilot.service;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.response.PaymentLinkResponse;

public interface PaymentService {

  public PaymentLinkResponse createPaymentLink(PlanType planType) throws Exception;

}
