package com.ProjectPilot.service;

import java.util.List;

import com.ProjectPilot.entities.Chat;
import com.ProjectPilot.entities.Project;
import com.ProjectPilot.entities.User;

public interface ProjectService {

  Project createProject(Project project , User user) throws Exception;
  
  List<Project> getProjectByTeam(User user,String category,String tag) throws Exception;

  Project getProjectbyId(Long projectId) throws Exception;

  void deleteProject(Long projectId , Long UserId) throws Exception;

  Project updateProject(Project updateProject,Long id) throws Exception;

  void addUserToProject(Long projectId,Long userId) throws Exception;

  void removeUserToProject(Long projectId,Long userId) throws Exception;

  Chat getChatByProjectId(Long projectId) throws Exception;

  List<Project> seachProjects(String keyword,User user) throws Exception;

}
