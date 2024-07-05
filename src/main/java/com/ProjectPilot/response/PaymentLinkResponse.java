package com.ProjectPilot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {

  private String paymentLinkId;
  private String paymentLinkURL;
}
