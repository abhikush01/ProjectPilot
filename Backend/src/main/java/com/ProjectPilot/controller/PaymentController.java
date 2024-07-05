package com.ProjectPilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.response.PaymentLinkResponse;
import com.ProjectPilot.service.PaymentService;
import com.ProjectPilot.service.SubscriptionService;
import com.ProjectPilot.service.UserService;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
        @PathVariable PlanType planType,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        PaymentLinkResponse paymentLinkResponse = paymentService.createPaymentLink(planType);
        subscriptionService.upgradeSubscription(user.getId(), planType);
       return ResponseEntity.ok(paymentLinkResponse);
    }
}
