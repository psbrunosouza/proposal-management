package com.moeb.proposalmanager.proposal.dto;

import com.moeb.proposalmanager.user.dto.UserResponseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProposalRequestDTO {
  @NotBlank(message = "O título é obrigatório")
  private String title;

  @NotNull(message = "O usuário é obrigatório")
  private UserResponseDTO user;

  public CreateProposalRequestDTO() {
  }

  public CreateProposalRequestDTO(String title, UserResponseDTO user) {
    this.title = title;
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public UserResponseDTO getUser() {
    return user;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUser(UserResponseDTO user) {
    this.user = user;
  }
}
