
package com.ProjectPilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.ChatRepository;
import com.ProjectPilot.entities.Chat;

@Service
public class ChatServiceImpl implements ChatService {

  @Autowired
  private ChatRepository chatRepository;

  @Override
  public Chat createChat(Chat chat) {
    return chatRepository.save(chat);
  }


}
