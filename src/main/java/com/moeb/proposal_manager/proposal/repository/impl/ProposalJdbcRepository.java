package com.moeb.proposal_manager.proposal.repository.impl;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.moeb.proposal_manager.proposal.model.Proposal;
import com.moeb.proposal_manager.proposal.repository.ProposalRepository;

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
}
