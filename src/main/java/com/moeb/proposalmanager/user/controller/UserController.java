package com.moeb.proposalmanager.user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeb.proposalmanager.shared.exception.ResourceNotFoundException;
import com.moeb.proposalmanager.user.dto.CreateUserRequestDTO;
import com.moeb.proposalmanager.user.dto.UserResponseDTO;
import com.moeb.proposalmanager.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> register(@RequestBody CreateUserRequestDTO dto) {
    UserResponseDTO createdUser = userService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
    Optional<UserResponseDTO> user = userService.findByEmail(email);
    return user.map((u) -> ResponseEntity.ok(u))
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }
}
