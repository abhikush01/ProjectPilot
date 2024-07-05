package com.ProjectPilot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

  private Long senderId;
  private Long projectId;
  private String content;

}
