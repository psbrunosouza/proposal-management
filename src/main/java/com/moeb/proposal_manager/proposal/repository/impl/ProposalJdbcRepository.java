package com.moeb.proposal_manager.proposal.repository.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.moeb.proposal_manager.proposal.model.Proposal;
import com.moeb.proposal_manager.proposal.repository.ProposalRepository;
import com.moeb.proposal_manager.user.model.User;

@Repository
public class ProposalJdbcRepository implements ProposalRepository {

  private final JdbcTemplate jdbcTemplate;

  public ProposalJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Proposal save(Proposal proposal) {
    String sql = """
            INSERT INTO proposals (id, title, created_at, user_id)
            VALUES (?, ?, ?, ?)
        """;

    proposal.setId(UUID.randomUUID());
    proposal.setCreatedAt(OffsetDateTime.now());

    jdbcTemplate.update(
        sql,
        proposal.getId(),
        proposal.getTitle(),
        proposal.getCreatedAt(),
        proposal.getUser().getId());

    return proposal;
  }

  @Override
  public List<Proposal> findAll() {
    String sql = """
          SELECT
          p.id as id,
          p.title as title,
          p.created_at as created_at,
          u.id AS user_id,
          u.name AS user_name,
          u.email AS user_email,
          u.created_at AS user_created_at
        FROM proposals p
        LEFT JOIN users u ON p.user_id = u.id
        """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      User user = new User(
          UUID.fromString(rs.getString("user_id")),
          rs.getString("user_name"),
          rs.getString("user_email"),
          rs.getObject("user_created_at", OffsetDateTime.class));

      return new Proposal(
          UUID.fromString(rs.getString("id")),
          rs.getString("title"),
          user,
          rs.getObject("created_at", OffsetDateTime.class));
    });
  }

  @Override
  public Optional<Proposal> findById(UUID id) {
    String sql = """
          SELECT
          p.id as id,
          p.title as title,
          p.created_at as created_at,
          u.id AS user_id,
          u.name AS user_name,
          u.email AS user_email,
          u.created_at AS user_created_at
        FROM proposals p
        LEFT JOIN users u ON p.user_id = u.id
        """;

    List<Proposal> proposals = jdbcTemplate.query(sql, (rs, rowNum) -> {
      User user = new User(
          UUID.fromString(rs.getString("user_id")),
          rs.getString("user_name"),
          rs.getString("user_email"),
          rs.getObject("user_created_at", OffsetDateTime.class));

      return new Proposal(
          UUID.fromString(rs.getString("id")),
          rs.getString("title"),
          user,
          rs.getObject("created_at", OffsetDateTime.class));
    });

    return proposals.stream().findFirst();
  }

  @Override
  public Proposal update(Proposal proposal) {

    System.err.println(proposal.getId());
    System.err.println(proposal.getTitle());

    String sql = """
        UPDATE proposals
        SET title = ?
        WHERE id = ?
        """;

    jdbcTemplate.update(sql, proposal.getTitle(), proposal.getId());

    return proposal;
  }

  @Override
  public void delete(UUID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
