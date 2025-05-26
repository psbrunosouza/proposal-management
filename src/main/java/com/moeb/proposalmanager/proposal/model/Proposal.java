package com.moeb.proposalmanager.proposal.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.moeb.proposalmanager.shared.model.BaseEntity;
import com.moeb.proposalmanager.user.model.User;

public class Proposal extends BaseEntity {
  private String title;
  private User user;

  public Proposal() {
    super();
  }

  public Proposal(UUID id, String title, User user, OffsetDateTime createdAt) {
    this.title = title;
    this.user = user;
    super.setId(id);
    super.setCreatedAt(createdAt);
  }

  public String getTitle() {
    return title;
  }

  public User getUser() {
    return user;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
