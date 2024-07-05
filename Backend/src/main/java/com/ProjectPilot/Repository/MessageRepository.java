package com.ProjectPilot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {

  List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}

