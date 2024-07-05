package com.ProjectPilot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectPilot.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

  User findByEmail(String email);
}
