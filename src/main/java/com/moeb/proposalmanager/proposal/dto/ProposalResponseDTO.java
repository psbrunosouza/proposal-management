package com.moeb.proposalmanager.proposal.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.moeb.proposalmanager.user.dto.UserResponseDTO;

public class ProposalResponseDTO {
  private UUID id;
  private String title;
  private UserResponseDTO user;
  private OffsetDateTime createdAt;

  public ProposalResponseDTO() {
  }

  public ProposalResponseDTO(UUID id, String title, UserResponseDTO user, OffsetDateTime createdAt) {
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

  public UserResponseDTO getUser() {
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

  public void setUser(UserResponseDTO user) {
    this.user = user;
  }
}
