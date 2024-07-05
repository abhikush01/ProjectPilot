package com.ProjectPilot.service;

import java.util.List;

import com.ProjectPilot.entities.Message;

public interface MessageService {

  Message sendMessage(Long senderId,Long projectId , String Content) throws Exception;

  List<Message> getMessageByProjectId(Long projectId) throws Exception;

}
