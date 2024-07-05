package com.ProjectPilot.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;

  private String description;

  private String status;

  @Column(name = "project_id")
  private Long projectId;

  private String priority;

  private LocalDate dueDate;

  private List<String> tags = new ArrayList<>();
  
  @ManyToOne
  @JoinColumn(name = "assignee_id") 
  private User assignee;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "project_id", insertable = false, updatable = false)
  private Project project;

  @JsonIgnore
  @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();
}
