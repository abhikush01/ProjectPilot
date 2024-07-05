package com.ProjectPilot.service;

import com.ProjectPilot.entities.Invitation;

public interface InvitationService {

  public void sentInvitataion(String email,Long projectId) throws Exception;

  public Invitation acceptInvitation(String token,Long userId) throws Exception;

  public String getTokenByUserMail(String userEmail);

  public void deleteToken(String Token);
} 
