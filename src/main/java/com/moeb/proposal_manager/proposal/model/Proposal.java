package com.moeb.proposal_manager.proposal.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.moeb.proposal_manager.shared.model.BaseEntity;
import com.moeb.proposal_manager.user.model.User;

public class Proposal extends BaseEntity {
  private String title;

  private User user;

  public Proposal() {
    super();
  }

  public Proposal(String title, User user) {
    this.title = title;
    this.user = user;
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
