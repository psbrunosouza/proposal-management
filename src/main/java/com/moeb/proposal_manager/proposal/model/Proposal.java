package com.moeb.proposal_manager.proposal.model;

import com.moeb.proposal_manager.shared.model.BaseEntity;
import com.moeb.proposal_manager.user.model.User;

public class Proposal extends BaseEntity {
  private String title;

  private User user;

  public Proposal() {
    super();
  }

  public Proposal(String title, User user) {
    super();
    this.title = title;
    this.user = user;
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
