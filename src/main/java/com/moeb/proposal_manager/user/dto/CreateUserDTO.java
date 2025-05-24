package com.moeb.proposal_manager.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
  @NotBlank(message = "A senha é obrigatória")
  @Size(min = 8, message = "A senha deve ter pelo menos 6 caracteres")
  private String password;

  @NotBlank(message = "O nome é obrigatório")
  private String name;

  @NotBlank(message = "O e-mail é obrigatório")
  @Email(message = "O e-mail deve ser válido")
  private String email;

  public CreateUserDTO() {
  }

  public CreateUserDTO(String name, String email, String password) {
    this.password = password;
    this.name = name;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}