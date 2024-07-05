package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.Project;
import com.ProjectPilot.entities.User;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project,Long>{

  // List<Project> findByOwner(User owner);

  List<Project> findByNameContainsAndTeamContains(String partialName , User user);

  // @Query("SELECT p FROM Project p JOIN p.team t WHERE t = :user")
  // List<Project> findProjectByTeam(@Param("user") User user);

  List<Project> findByTeamContainsOrOwner(User user , User owner);
} 