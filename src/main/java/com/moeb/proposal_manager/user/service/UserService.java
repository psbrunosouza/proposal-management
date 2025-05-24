package com.moeb.proposal_manager.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moeb.proposal_manager.shared.exception.ConflictException;
import com.moeb.proposal_manager.user.dto.CreateUserDTO;
import com.moeb.proposal_manager.user.dto.UserDTO;
import com.moeb.proposal_manager.user.model.User;
import com.moeb.proposal_manager.user.repository.UserRepository;

@Service
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public UserDTO create(CreateUserDTO dto) {
    if (this.findUsername(dto.getEmail()).isPresent()) {
      throw new ConflictException("Email " + dto.getEmail() + " is already taken");
    }

    User user = new User(dto.getName(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));

    User savedUser = userRepository.save(user);

    return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
        savedUser.getCreatedAt());
  }

  public Optional<UserDTO> findByEmail(String email) {
    return userRepository.findByEmail(email)
        .map((u) -> new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getCreatedAt()));
  }

  public Optional<User> findUsername(String email) {
    return userRepository.findByEmail(email)
        .map((u) -> new User(u.getId(), u.getName(), u.getEmail(), u.getPassword()));
  }
}
