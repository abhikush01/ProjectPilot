package com.ProjectPilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.entities.Chat;
import com.ProjectPilot.entities.Message;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.MessageRequest;
import com.ProjectPilot.service.MessageService;
import com.ProjectPilot.service.ProjectService;
import com.ProjectPilot.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @PostMapping("/send")
  public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest req) throws Exception {
      
    User user = userService.findUserById(req.getSenderId());
    if(user == null) throw new Exception("User not found with id "+req.getSenderId());
    Chat chats = projectService.getProjectbyId(req.getProjectId()).getChat();
    if(chats == null) throw new Exception("Chat not found");
    Message sentMessage = messageService.sendMessage(req.getSenderId(), req.getProjectId(), req.getContent());
    return ResponseEntity.ok(sentMessage);
  }

  @GetMapping("/chat/{projectId}")
  public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId) throws Exception {
      List<Message> messages = messageService.getMessageByProjectId(projectId);
      return ResponseEntity.ok(messages);
  }
  
  
}
