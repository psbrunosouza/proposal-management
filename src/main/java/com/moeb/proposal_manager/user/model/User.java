package com.moeb.proposal_manager.user.model;

import java.util.List;
import java.util.UUID;

import com.moeb.proposal_manager.proposal.model.Proposal;
import com.moeb.proposal_manager.shared.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Proposal> proposals;

  public User() {
  }

  public User(UUID id, String name, String email) {
    super();
    this.name = name;
    this.email = email;
    super.setId(id);
  }

  public User(String name, String email, String password) {
    super();
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User(UUID id, String name, String email, String password) {
    super();
    this.name = name;
    this.email = email;
    this.password = password;
    super.setId(id);
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
