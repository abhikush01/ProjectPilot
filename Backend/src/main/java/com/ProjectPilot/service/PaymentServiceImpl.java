package com.ProjectPilot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.response.PaymentLinkResponse;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Value("${stripe.secret.key}")
  private String secretKey;

  @Override
  public PaymentLinkResponse createPaymentLink(PlanType planType) throws Exception {

    int amount = 799 * 100;
    if (planType.equals(PlanType.ANNUAL)) {
        amount = amount * 12;
        amount = (int) (amount * 0.7);
    }

    // Debug: Print the secret key (Remove this after debugging)
    System.out.println("Stripe Secret Key: " + secretKey);

    Stripe.apiKey = secretKey;

    try {
      SessionCreateParams params = SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:5173/upgrade_plan/success?planType=" + planType)
            .setCancelUrl("http://localhost:8080/upgrade_plan/fail")
            .addLineItem(SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                   .setCurrency("inr")
                   .setUnitAmount((long) amount)
                   .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                       .setName("Payment for plan: " + planType)
                          .build())
                        .build())
                    .build())
                .build();

        Session session = Session.create(params);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        paymentLinkResponse.setPaymentLinkURL(session.getUrl());
        paymentLinkResponse.setPaymentLinkId(session.getId());

        System.out.println("Response sent to frontend: " + paymentLinkResponse);

        return paymentLinkResponse;
    } catch (Exception e) {
      // Debug: Print the exception message
      System.out.println("Exception occurred: " + e.getMessage());
      throw e;
    }
  }
}
