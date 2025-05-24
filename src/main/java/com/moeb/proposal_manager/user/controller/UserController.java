package com.moeb.proposal_manager.user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeb.proposal_manager.shared.exception.ResourceNotFoundException;
import com.moeb.proposal_manager.user.dto.CreateUserDTO;
import com.moeb.proposal_manager.user.dto.UserDTO;
import com.moeb.proposal_manager.user.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@Valid @RequestBody CreateUserDTO dto) {
    UserDTO createdUser = userService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
    Optional<UserDTO> user = userService.findByEmail(email);
    return user.map((u) -> ResponseEntity.ok(u))
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }
}
