package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Issue;
import java.util.List;


public interface IssueRepository extends JpaRepository<Issue,Long> {

  public List<Issue>  findByProjectId(Long projectId);
}
