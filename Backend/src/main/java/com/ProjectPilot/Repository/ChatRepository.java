package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat,Long> {

}
