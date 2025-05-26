package com.moeb.proposalmanager.proposalsection.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.moeb.proposalmanager.proposal.dto.ProposalResponseDTO;
import com.moeb.proposalmanager.proposalsection.types.ProposalSectionType;

public class ProposalSectionResponseDTO {
  private UUID id;
  private ProposalSectionType type;
  private OffsetDateTime createdAt;
  private ProposalResponseDTO proposal;
  private String content;

  public ProposalSectionResponseDTO(
      UUID id,
      ProposalSectionType type,
      ProposalResponseDTO proposal,
      String content,
      OffsetDateTime createdAt) {
    this.id = id;
    this.type = type;
    this.proposal = proposal;
    this.content = content;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public ProposalResponseDTO getProposal() {
    return proposal;
  }

  public ProposalSectionType getType() {
    return type;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setProposal(ProposalResponseDTO proposal) {
    this.proposal = proposal;
  }

  public void setType(ProposalSectionType type) {
    this.type = type;
  }
}
