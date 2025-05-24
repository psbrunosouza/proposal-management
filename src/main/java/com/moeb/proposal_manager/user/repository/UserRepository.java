package com.moeb.proposal_manager.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.moeb.proposal_manager.user.model.User;

public interface UserRepository {
  Optional<User> findByEmail(String email);

  Optional<User> findById(UUID id);

  User save(User user);
}
