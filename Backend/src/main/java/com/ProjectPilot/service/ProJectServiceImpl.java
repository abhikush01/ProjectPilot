package com.ProjectPilot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.ProjectRepository;
import com.ProjectPilot.entities.Chat;
import com.ProjectPilot.entities.Project;
import com.ProjectPilot.entities.User;

@Service
public class ProJectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ChatService chatService;
  
  @Override
  public Project createProject(Project project, User user) throws Exception {
   Project createProject = new Project();
   createProject.setOwner(user);
   createProject.setTags(project.getTags());
   createProject.setName(project.getName());
   createProject.setCategory(project.getCategory());
   createProject.setDescription(project.getDescription());
   createProject.getTeam().add(user);

   Project savedProject = projectRepository.save(createProject);

   Chat chat = new Chat();
   chat.setProject(savedProject);
   Chat projectChat = chatService.createChat(chat);
   savedProject.setChat(projectChat);

   return savedProject;
  }

  @Override
  public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
    List<Project> projects = projectRepository.findByTeamContainsOrOwner(user, user);
    if(category != null){
      projects = projects.stream().filter(project -> project.getCategory().equals(category)).collect(Collectors.toList());
    }

    if(tag != null){
      projects = projects.stream().filter(project -> project.getTags().contains(tag)).collect(Collectors.toList());
    }

    return projects;
  }

  @Override
  public Project getProjectbyId(Long projectId) throws Exception {
   Optional<Project> project = projectRepository.findById(projectId);
   if(project.isEmpty()){
    throw new Exception("project Not Found");
   }
   return project.get();
  }

  @Override
  public void deleteProject(Long projectId, Long UserId) throws Exception {
    projectRepository.deleteById(projectId);
  }

  @Override
  public Project updateProject(Project updateProject, Long id) throws Exception {
    Project project = getProjectbyId(id);

    project.setName(updateProject.getName());
    project.setDescription(updateProject.getDescription());
    project.setTags( updateProject.getTags());

    return projectRepository.save(project);

  }

  @Override
  public void addUserToProject(Long projectId,Long userId) throws Exception {
    Project project = getProjectbyId(projectId);
    User user = userService.findUserById(userId);
    if(!project.getTeam().contains(user)){
      project.getChat().getUsers().add(user);
      project.getTeam().add(user);

    }
    projectRepository.save(project);
  }

  @Override
  public void removeUserToProject(Long projectId,Long userId) throws Exception {
    Project project = getProjectbyId(projectId);
    User user = userService.findUserById(userId);
    if(project.getTeam().contains(user)){
      project.getChat().getUsers().remove(user);
      project.getTeam().remove(user);

    }
    projectRepository.save(project);
  }

  @Override
  public Chat getChatByProjectId(Long projectId) throws Exception {
    Project project = getProjectbyId(projectId);
    
    return project.getChat(); 
  }

  @Override
  public List<Project> seachProjects(String keyword, User user) throws Exception {
    List<Project> projects = projectRepository.findByNameContainsAndTeamContains(keyword, user);
    return projects;
  }

}
