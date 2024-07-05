package com.ProjectPilot.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.InvitationRepository;
import com.ProjectPilot.entities.Invitation;

@Service
public class InvitationServiceImpl implements InvitationService{

  @Autowired
  private InvitationRepository invitationRepository;

  @Autowired
  private EmailService emailService;

  @Override
  public void sentInvitataion(String email, Long projectId) throws Exception {
    String invitationToken = UUID.randomUUID().toString();
    
    Invitation invitation = new Invitation();
    invitation.setEmail(email);
    invitation.setProjectId(projectId);
    invitation.setToken(invitationToken);

    invitationRepository.save(invitation);

    String invitationLink = "http://localhost:5173/accept_invitation?token="+invitationToken;

    emailService.sendEmailWithToken(email, invitationLink);

  }

  @Override
  public Invitation acceptInvitation(String token, Long userId) throws Exception {
    Invitation invitation = invitationRepository.findByToken(token);
    if(invitation == null) {
      throw new Exception("Invalid Invitation not found");
    }
    return invitation;
  }

  @Override
  public String getTokenByUserMail(String userEmail) {
    Invitation invitation = invitationRepository.findByEmail(userEmail);
    return invitation.getToken();
  }

  @Override
  public void deleteToken(String Token) {
    Invitation invitation = invitationRepository.findByToken(Token);
    invitationRepository.delete(invitation);
  }

}
