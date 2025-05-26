package com.moeb.proposalmanager.proposalsection.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.proposalsection.types.ProposalSectionType;
import com.moeb.proposalmanager.shared.model.BaseEntity;

public class ProposalSection extends BaseEntity {
  private ProposalSectionType type;
  private Proposal proposal;
  private String content;

  public ProposalSection(UUID id, ProposalSectionType type, Proposal proposal, String content,
      OffsetDateTime createdAt) {
    super.setId(id);
    super.setCreatedAt(createdAt);
    this.type = type;
    this.proposal = proposal;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public Proposal getProposal() {
    return proposal;
  }

  public ProposalSectionType getType() {
    return type;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setProposal(Proposal proposal) {
    this.proposal = proposal;
  }

  public void setType(ProposalSectionType type) {
    this.type = type;
  }
}
