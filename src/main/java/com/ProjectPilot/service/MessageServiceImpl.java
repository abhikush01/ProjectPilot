package com.ProjectPilot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.MessageRepository;
import com.ProjectPilot.Repository.UserRepository;
import com.ProjectPilot.entities.Chat;
import com.ProjectPilot.entities.Message;
import com.ProjectPilot.entities.User;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProjectService projectService;


  @Override
  public Message sendMessage(Long senderId, Long projectId, String Content) throws Exception {
    User sender = userRepository.findById(senderId).orElseThrow(() -> new Exception("User not Found "+senderId));

    Chat chat = projectService.getProjectbyId(projectId).getChat();

    Message msg = new Message();
    msg.setChat(chat);
    msg.setContent(Content);
    msg.setSender(sender);
    msg.setCreatedAt(LocalDateTime.now());

    Message savedMessage = messageRepository.save(msg);
    chat.getMeassages().add(savedMessage);
    return savedMessage;
    
  }

  @Override
  public List<Message> getMessageByProjectId(Long projectId) throws Exception {
    Chat chat = projectService.getChatByProjectId(projectId);
    List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    return messages;
  }

}
