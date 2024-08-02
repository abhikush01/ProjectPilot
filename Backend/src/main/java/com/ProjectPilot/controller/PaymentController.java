package com.ProjectPilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProjectPilot.entities.PlanType;
import com.ProjectPilot.response.PaymentLinkResponse;
import com.ProjectPilot.service.PaymentService;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {

 

    @Autowired
    private PaymentService paymentService;

    

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
        @PathVariable PlanType planType,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
      
        PaymentLinkResponse paymentLinkResponse = paymentService.createPaymentLink(planType);

       return ResponseEntity.ok(paymentLinkResponse);
    }
}
