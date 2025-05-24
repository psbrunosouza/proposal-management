package com.moeb.proposal_manager.proposal.model;

import com.moeb.proposal_manager.shared.model.BaseEntity;
import com.moeb.proposal_manager.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
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
