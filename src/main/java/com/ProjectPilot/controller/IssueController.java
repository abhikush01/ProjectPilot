package com.ProjectPilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.DTO.IssueDTO;
import com.ProjectPilot.entities.Issue;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.IssueRequest;
import com.ProjectPilot.response.MessageResponse;
import com.ProjectPilot.service.IssueService;
import com.ProjectPilot.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/issues")
public class IssueController {

  @Autowired
  private IssueService issueService;

  @Autowired
  private UserService userService;

  @GetMapping("/{issueId}")
  public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
      return ResponseEntity.ok(issueService.getIssueById(issueId));
  }

  @GetMapping("/project/{projectId}")
  public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
      return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
  }

  @PostMapping
  public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,@RequestHeader("Authorization") String Authorization) throws Exception{
    User tokenUser = userService.findUserProfileByJwt(Authorization);
    User user = userService.findUserById(tokenUser.getId());

    if(user != null){
      Issue createdIssue = issueService.createIssue(issue, user);
      IssueDTO issueDTO = new IssueDTO();
      issueDTO.setDescription(createdIssue.getDescription());
      issueDTO.setDueDate(createdIssue.getDueDate());
      issueDTO.setId(createdIssue.getId());
      issueDTO.setPriority(createdIssue.getPriority());
      issueDTO.setProject(createdIssue.getProject());
      issueDTO.setProjectId(createdIssue.getProjectId());
      issueDTO.setStatus(createdIssue.getStatus());
      issueDTO.setTitle(createdIssue.getTitle());
      issueDTO.setTags(createdIssue.getTags());
      issueDTO.setAssignee(createdIssue.getAssignee());

      return new ResponseEntity<>(issueDTO,HttpStatus.CREATED);
    }
    else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }


  @DeleteMapping("/{issueId}")
  public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId , @RequestHeader("Authorization") String Authorization) throws Exception{
    User user = userService.findUserProfileByJwt(Authorization);
    issueService.deleteIssue(issueId, user.getId());

    MessageResponse res = new MessageResponse();
    res.setMessage("Issue Deleted");
    
    return ResponseEntity.ok(res);
  }

  @PutMapping("/{IssueId}/assignee/{userId}")
  public ResponseEntity<Issue> addUserToIssue(@PathVariable Long IssueId,@PathVariable Long userId) throws Exception {
      Issue issue = issueService.addUserToIssue(IssueId, userId);
      return ResponseEntity.ok(issue);
  }

  @PutMapping("/{IssueId}/status/{status}")
  public ResponseEntity<Issue> updateStatus(@PathVariable Long IssueId,@PathVariable String status) throws Exception {
      Issue issue = issueService.updateStatus(IssueId, status);
      return ResponseEntity.ok(issue);
  }

  
}
