package com.ProjectPilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectPilot.entities.Comment;
import com.ProjectPilot.entities.User;
import com.ProjectPilot.request.CommentRequest;
import com.ProjectPilot.response.MessageResponse;
import com.ProjectPilot.service.CommentService;
import com.ProjectPilot.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @PostMapping()
  public ResponseEntity<Comment> createComment(
    @RequestBody CommentRequest request,
    @RequestHeader("Authorization") String jwt
  ) throws Exception {
      User user = userService.findUserProfileByJwt(jwt);
      Comment createdComment = commentService.createComment(request.getIssueId(), user.getId(), request.getContent());
      
      
      return ResponseEntity.ok(createdComment);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<MessageResponse> deleteComment(
    @PathVariable Long commentId,
    @RequestHeader("Authorization") String jwt
  ) throws Exception {

      User user = userService.findUserProfileByJwt(jwt);
      commentService.deleteComment(commentId, user.getId());
      
      MessageResponse msg = new MessageResponse();

      msg.setMessage("Commnet Deleted Successfully");
      
      return ResponseEntity.ok(msg);
  }

  @GetMapping("/{issueId}")
  public ResponseEntity<List<Comment>> getCommentByIssueId(
    @PathVariable Long issueId
  ) throws Exception {

    List<Comment> comments = commentService.findCommentByIssueId(issueId);
    
    return ResponseEntity.ok(comments); 
  }
  
  

}
