package com.moeb.proposalmanager.proposal.dto;

public class UpdateProposalRequestDTO {
  private String title;

  public UpdateProposalRequestDTO(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
