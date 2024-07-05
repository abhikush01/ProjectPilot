package com.ProjectPilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.entities.Chat;
import com.ProjectPilot.entities.Invitation;
import com.ProjectPilot.entities.Project;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.InviteRequest;
import com.ProjectPilot.response.MessageResponse;
import com.ProjectPilot.service.InvitationService;
import com.ProjectPilot.service.ProjectService;
import com.ProjectPilot.service.UserService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;

  @Autowired
  private InvitationService invitationService;

  @GetMapping
  public ResponseEntity<List<Project>> getProjects (
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String tag,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    User user = userService.findUserProfileByJwt(jwt);
    List<Project> projects = projectService.getProjectByTeam(user, category, tag);
    return new ResponseEntity<>(projects,HttpStatus.OK);

  }

  @GetMapping("/{projectId}")
  public ResponseEntity<Project> getProjectById (
    @PathVariable Long projectId,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    Project project = projectService.getProjectbyId(projectId);
    return new ResponseEntity<>(project,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Project> createProject (
    @RequestBody Project project,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    User user = userService.findUserProfileByJwt(jwt);
    Project createdProject = projectService.createProject(project, user);
    return new ResponseEntity<>(createdProject,HttpStatus.OK);

  }


  @PatchMapping("/{projectId}")
  public ResponseEntity<Project> updateProject (
    @PathVariable Long projectId,
    @RequestBody Project project,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    Project updatedProject = projectService.updateProject(project, projectId);
    return new ResponseEntity<>(updatedProject,HttpStatus.OK);
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<MessageResponse> deleteProject (
    @PathVariable Long projectId,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    User user = userService.findUserProfileByJwt(jwt);
    projectService.deleteProject(projectId, user.getId());
    return new ResponseEntity<>(new MessageResponse("Project Deleted SuccessFully"),HttpStatus.OK);
  }


  @GetMapping("/search")
  public ResponseEntity<List<Project>> searchProjects (
    @RequestParam(required = false) String keyword,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    User user = userService.findUserProfileByJwt(jwt);
    List<Project> projects = projectService.seachProjects(keyword, user);
    return new ResponseEntity<>(projects,HttpStatus.OK);

  }

  @GetMapping("/{projectId}/chat")
  public ResponseEntity<Chat> getChatByProjectId (
    @PathVariable Long projectId,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
    Chat chat = projectService.getChatByProjectId(projectId);
    return new ResponseEntity<>(chat,HttpStatus.OK);
  }


  @PostMapping("/invite")
  public ResponseEntity<MessageResponse> inviteProject (
    @RequestBody InviteRequest inviteRequest,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {
   invitationService.sentInvitataion(inviteRequest.getEmail(), inviteRequest.getProjectId());
   MessageResponse res = new MessageResponse("User Invited For Project Successfully");

  return new ResponseEntity<>(res,HttpStatus.OK);
  }

  @GetMapping("/accept_invite")
  public ResponseEntity<Invitation> acceptInvite (
    @RequestParam String token,
    @RequestHeader("Authorization") String jwt
    ) throws Exception
  {

   User user = userService.findUserProfileByJwt(jwt);
   Invitation invitation = invitationService.acceptInvitation(token, user.getId());
   projectService.addUserToProject(invitation.getProjectId(), invitation.getProjectId());
    return new ResponseEntity<>(invitation,HttpStatus.ACCEPTED);
  }




}
