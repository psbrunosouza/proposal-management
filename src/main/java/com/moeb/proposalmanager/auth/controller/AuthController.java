package com.moeb.proposalmanager.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeb.proposalmanager.auth.dto.AuthResponseDTO;
import com.moeb.proposalmanager.auth.dto.LoginDTO;
import com.moeb.proposalmanager.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginRequest) {
    String token = authService.authenticate(loginRequest);
    return ResponseEntity.ok(new AuthResponseDTO(token));
  }
}
