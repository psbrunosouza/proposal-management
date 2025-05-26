package com.moeb.proposalmanager.proposalsection.dto;

import com.moeb.proposalmanager.proposal.dto.ProposalResponseDTO;
import com.moeb.proposalmanager.proposalsection.types.ProposalSectionType;

public class UpdateProposalSectionRequestDTO {
  private ProposalSectionType type;
  private ProposalResponseDTO proposal;
  private String content;

  public UpdateProposalSectionRequestDTO(
      ProposalSectionType type,
      ProposalResponseDTO proposal,
      String content) {
    this.type = type;
    this.proposal = proposal;
    this.content = content;
  }

  public String getContent() {
    return content;
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

  public void setProposal(ProposalResponseDTO proposal) {
    this.proposal = proposal;
  }

  public void setType(ProposalSectionType type) {
    this.type = type;
  }
}
