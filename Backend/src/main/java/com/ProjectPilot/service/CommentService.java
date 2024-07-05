package com.ProjectPilot.service;

import java.util.List;

import com.ProjectPilot.entities.Comment;

public interface CommentService {

  Comment createComment(Long issueId , Long userId,String content) throws Exception;

  void deleteComment(Long commentId,Long userId) throws Exception;

  List<Comment> findCommentByIssueId(Long issueId) throws Exception;

}
