package com.moeb.proposal_manager.proposal.dto;

public class UpdateProposalDTO {
  private String title;

  public UpdateProposalDTO(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
