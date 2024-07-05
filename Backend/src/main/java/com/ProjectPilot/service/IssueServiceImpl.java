package com.ProjectPilot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.IssueRepository;
import com.ProjectPilot.entities.Issue;
import com.ProjectPilot.entities.Project;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.IssueRequest;

@Service
public class IssueServiceImpl implements IssueService {

  @Autowired
  private IssueRepository issueRepository;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;


  @Override
  public Issue getIssueById(Long issueId) throws Exception {
    Optional<Issue> issue = issueRepository.findById(issueId);
    if(issue.isEmpty()){
      throw new Exception("No Issue found with Id " + issueId);
    }
    return issue.get();
  }

  @Override
  public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
    return issueRepository.findByProjectId(projectId);
  }

  @Override
  public Issue createIssue(IssueRequest issue, User user) throws Exception {
   Project project = projectService.getProjectbyId(issue.getProjectId());

   Issue createdIssue = new Issue();
   createdIssue.setTitle(issue.getTitle());
   createdIssue.setDescription(issue.getDescription());
   createdIssue.setStatus(issue.getStatus());
   createdIssue.setProjectId(issue.getProjectId());
   createdIssue.setPriority(issue.getPriority());
   createdIssue.setDueDate(issue.getDueDate());

   createdIssue.setProject(project);

   return issueRepository.save(createdIssue);

  }

  @Override
  public void deleteIssue(Long issueId, Long userId) throws Exception {
  
    issueRepository.deleteById(issueId);
    
  }

  @Override
  public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
   User user = userService.findUserById(userId);
   Issue issue = getIssueById(issueId); 
   issue.setAssignee(user);
   return issueRepository.save(issue);
  }

  @Override
  public Issue updateStatus(Long issueId, String status) throws Exception {
    Issue issue = getIssueById(issueId); 
    issue.setStatus(status);
    return issueRepository.save(issue);
  }

}
