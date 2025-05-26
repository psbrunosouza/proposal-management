package com.moeb.proposalmanager.user.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UserResponseDTO {
  private UUID id;
  private String name;
  private String email;
  private OffsetDateTime createdAt;

  public UserResponseDTO() {
  }

  public UserResponseDTO(UUID id, String name, String email, OffsetDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
