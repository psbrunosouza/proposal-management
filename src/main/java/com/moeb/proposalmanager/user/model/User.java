package com.moeb.proposalmanager.user.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.shared.model.BaseEntity;

public class User extends BaseEntity {
  private String name;
  private String email;
  private String password;
  private List<Proposal> proposals;

  public User() {
  }

  public User(UUID id, String name, String email, String password, OffsetDateTime createdAt) {
    this.name = name;
    this.email = email;
    this.password = password;
    super.setId(id);
    super.setCreatedAt(createdAt);
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public List<Proposal> getProposals() {
    return proposals;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setProposals(List<Proposal> proposals) {
    this.proposals = proposals;
  }
}
