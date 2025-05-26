package com.moeb.proposalmanager.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.moeb.proposalmanager.user.model.User;

public interface UserRepository {
  Optional<User> findByEmail(String email);

  Optional<User> findById(UUID id);

  User save(User user);
}
