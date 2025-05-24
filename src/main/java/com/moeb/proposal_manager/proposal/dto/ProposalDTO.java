package com.moeb.proposal_manager.proposal.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.moeb.proposal_manager.user.dto.UserDTO;

public class ProposalDTO {
  private UUID id;
  private String title;
  private UserDTO user;
  private OffsetDateTime createdAt;

  public ProposalDTO() {
  }

  public ProposalDTO(UUID id, String title, UserDTO user, OffsetDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.user = user;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getTitle() {
    return title;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }
}
