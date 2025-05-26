package com.moeb.proposalmanager.user.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moeb.proposalmanager.shared.exception.ConflictException;
import com.moeb.proposalmanager.user.dto.CreateUserRequestDTO;
import com.moeb.proposalmanager.user.dto.UserResponseDTO;
import com.moeb.proposalmanager.user.mapper.UserMapper;
import com.moeb.proposalmanager.user.model.User;
import com.moeb.proposalmanager.user.repository.UserRepository;

@Service
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public UserResponseDTO create(CreateUserRequestDTO dto) {
    if (this.findUsername(dto.getEmail()).isPresent()) {
      throw new ConflictException("Email " + dto.getEmail() + " is already taken");
    }

    dto.setPassword(passwordEncoder.encode(dto.getPassword()));

    User savedUser = userRepository.save(UserMapper.toEntity(dto));

    return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
        savedUser.getCreatedAt());
  }

  public Optional<UserResponseDTO> findByEmail(String email) {
    return userRepository.findByEmail(email)
        .map((u) -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getCreatedAt()));
  }

  public Optional<User> findUsername(String email) {
    return userRepository.findByEmail(email)
        .map((u) -> new User(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getCreatedAt()));
  }
}
