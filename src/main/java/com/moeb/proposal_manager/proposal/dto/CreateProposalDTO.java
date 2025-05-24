package com.moeb.proposal_manager.proposal.dto;

import com.moeb.proposal_manager.user.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProposalDTO {
  @NotBlank(message = "O título é obrigatório")
  private String title;

  @NotNull(message = "O usuário é obrigatório")
  private UserDTO user;

  public CreateProposalDTO() {
  }

  public CreateProposalDTO(String title, UserDTO user) {
    this.title = title;
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }
}
