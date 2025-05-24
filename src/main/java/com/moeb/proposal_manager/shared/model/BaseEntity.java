package com.moeb.proposal_manager.shared.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class BaseEntity {
  private UUID id;
  private OffsetDateTime createdAt;

  public UUID getId() {
    return id;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
