package com.ProjectPilot.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectPilot.Repository.CommnetRepository;
import com.ProjectPilot.Repository.IssueRepository;
import com.ProjectPilot.Repository.UserRepository;
import com.ProjectPilot.entities.Comment;
import com.ProjectPilot.entities.Issue;
import com.ProjectPilot.entities.User;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommnetRepository commnetRepository;

  @Autowired
  private IssueRepository issueRepository;

  @Autowired
  private UserRepository userRepository;
  
  @Override
  public Comment createComment(Long issueId, Long userId, String content) throws Exception {
    Optional<Issue> Opissue = issueRepository.findById(issueId);
    Optional<User> Opuser = userRepository.findById(userId);

    if(Opissue.isEmpty())
     throw new Exception("Issue not found with id " + issueId);

    if(Opuser.isEmpty()) throw new Exception("User not found with id "+userId);

    Issue issue = Opissue.get();
    User user = Opuser.get();

    Comment comment = new Comment();

    comment.setIssue(issue);
    comment.setUser(user);
    comment.setContent(content);
    comment.setCreatedAt(LocalDateTime.now());

    Comment savedComment = commnetRepository.save(comment);

    issue.getComments().add(savedComment);

    return savedComment;

  }

  @Override
  public void deleteComment(Long commentId, Long userId) throws Exception {
    Optional<Comment> OpComment = commnetRepository.findById(commentId);
    Optional<User> OpUser = userRepository.findById(userId);

    if(OpComment.isEmpty())
     throw new Exception("Comment not found with id " + commentId);

    if(OpUser.isEmpty()) throw new Exception("User not found with id "+userId);

    Comment comment = OpComment.get();
    User user = OpUser.get();

    if(comment.getUser().equals(user)){
      commnetRepository.delete(comment);
    }
    else throw new Exception("User does not have permission to delete");
  }

  @Override
  public List<Comment> findCommentByIssueId(Long issueId) throws Exception {
    return commnetRepository.findByIssueId(issueId);
  }

}
