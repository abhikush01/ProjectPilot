package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {

  Invitation findByToken(String token);

  Invitation findByEmail(String email);
}
