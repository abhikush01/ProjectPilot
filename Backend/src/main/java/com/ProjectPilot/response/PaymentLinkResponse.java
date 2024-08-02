package com.ProjectPilot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentLinkResponse {

  private String paymentLinkId;
  private String paymentLinkURL;
}
