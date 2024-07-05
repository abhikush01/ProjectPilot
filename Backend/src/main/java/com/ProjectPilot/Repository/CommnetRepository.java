package com.ProjectPilot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Comment;

public interface CommnetRepository extends JpaRepository<Comment,Long> {

  List<Comment> findByIssueId(Long issueId);
}
