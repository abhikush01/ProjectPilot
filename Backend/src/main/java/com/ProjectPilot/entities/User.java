package com.ProjectPilot.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "users")
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String fullname;
  private String email;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
  private List<Issue> assignedIssues = new ArrayList<>();
  
  private int projectSize;

}
