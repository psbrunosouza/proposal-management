package com.moeb.proposalmanager.user.repository.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moeb.proposalmanager.user.model.User;
import com.moeb.proposalmanager.user.repository.UserRepository;

@Repository
public class UserJdbcRepository implements UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    String sql = """
        SELECT id, name, email, password, created_at FROM users
        WHERE email = ?
        """;
    ;

    List<User> users = jdbcTemplate.query(sql, (resultSet, rowNum) -> new User(
        UUID.fromString(resultSet.getString("id")),
        resultSet.getString("name"),
        resultSet.getString("email"),
        resultSet.getString("password"),
        resultSet.getObject("created_at", OffsetDateTime.class)), email);

    return users.stream().findFirst();
  }

  @Override
  public User save(User user) {

    String sql = """
          INSERT INTO users (id, name, email, password, created_at)
          VALUES (?, ?, ?, ?, ?)
        """;

    user.setId(UUID.randomUUID());
    user.setCreatedAt(OffsetDateTime.now());

    jdbcTemplate.update(sql,
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getCreatedAt());

    return user;
  }

  @Override
  public Optional<User> findById(UUID id) {
    String sql = """
        SELECT id, name, email, password, created_at FROM users
        WHERE id = ?
        """;
    ;

    List<User> users = jdbcTemplate.query(sql, (resultSet, rowNum) -> new User(
        UUID.fromString(resultSet.getString("id")),
        resultSet.getString("name"),
        resultSet.getString("email"),
        resultSet.getString("password"),
        resultSet.getObject("created_at", OffsetDateTime.class)), id);

    return users.stream().findFirst();
  }

}
