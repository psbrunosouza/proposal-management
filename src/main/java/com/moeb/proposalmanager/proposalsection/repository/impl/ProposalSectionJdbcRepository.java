package com.moeb.proposalmanager.proposalsection.repository.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.proposalsection.model.ProposalSection;
import com.moeb.proposalmanager.proposalsection.repository.ProposalSectionRepository;
import com.moeb.proposalmanager.proposalsection.types.ProposalSectionType;

@Repository
public class ProposalSectionJdbcRepository implements ProposalSectionRepository {

  private final JdbcTemplate jdbcTemplate;

  public ProposalSectionJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public ProposalSection create(ProposalSection proposalSection) {
    String sql = """
        INSERT INTO proposal_sections (id, type, proposal_id, created_at, content)
        VALUES (?, ?::SECTION_TYPE, ?, ?, ?::jsonb);
        """;

    proposalSection.setId(UUID.randomUUID());
    proposalSection.setCreatedAt(OffsetDateTime.now());

    System.err.println(proposalSection.getType());

    jdbcTemplate.update(
        sql,
        proposalSection.getId(),
        proposalSection.getType().toString(),
        proposalSection.getProposal().getId(),
        proposalSection.getCreatedAt(),
        proposalSection.getContent());

    return proposalSection;
  }

  @Override
  public Optional<ProposalSection> findById(UUID id) {
    String sql = """
        SELECT
            ps.id as ps_id,
            ps.type as ps_type,
            ps.proposal_id as ps_proposal_id,
            ps.created_at as ps_created_at,
            ps.content as ps_content,
            p.id as p_id,
            p.title as p_title,
            p.created_at as p_created_at
        FROM proposal_sections ps
        LEFT JOIN proposals p ON ps.proposal_id = p.id
        WHERE ps.id = ?;
        """;

    List<ProposalSection> proposalSection = jdbcTemplate.query(sql, (rs, rowNum) -> {
      Proposal proposal = new Proposal(
          UUID.fromString(rs.getString("p_id")),
          rs.getString("p_title"),
          null,
          rs.getObject("p_created_at", OffsetDateTime.class));

      return new ProposalSection(
          UUID.fromString(rs.getString("ps_id")),
          ProposalSectionType.valueOf(rs.getString("ps_type")),
          proposal,
          rs.getString("ps_content"),
          rs.getObject("ps_created_at", OffsetDateTime.class));
    }, id);

    return proposalSection.stream().findFirst();
  }

  @Override
  public void delete(UUID id) {
    String sql = """
        DELETE FROM proposal_sections WHERE id = ?;
        """;

    jdbcTemplate.update(sql, id);
  }

  @Override
  public ProposalSection update(ProposalSection proposalSection) {
    String sql = """
        UPDATE proposal_sections
        SET type = ?::SECTION_TYPE,
            proposal_id = ?,
            content = ?::jsonb
        WHERE id = ?;
        """;

    jdbcTemplate.update(
        sql,
        proposalSection.getType().toString(),
        proposalSection.getProposal().getId(),
        proposalSection.getContent(),
        proposalSection.getId());

    return proposalSection;
  }

  @Override
  public List<ProposalSection> findAll() {
    String sql = """
        SELECT
            ps.id as ps_id,
            ps.type as ps_type,
            ps.proposal_id as ps_proposal_id,
            ps.created_at as ps_created_at,
            ps.content as ps_content,
            p.id as p_id,
            p.title as p_title,
            p.created_at as p_created_at
        FROM proposal_sections ps
        LEFT JOIN proposals p ON ps.proposal_id = p.id;
        """;

    List<ProposalSection> proposalSection = jdbcTemplate.query(sql, (rs, rowNum) -> {
      Proposal proposal = new Proposal(
          UUID.fromString(rs.getString("p_id")),
          rs.getString("p_title"),
          null,
          rs.getObject("p_created_at", OffsetDateTime.class));

      return new ProposalSection(
          UUID.fromString(rs.getString("ps_id")),
          ProposalSectionType.valueOf(rs.getString("ps_type")),
          proposal,
          rs.getString("ps_content"),
          rs.getObject("ps_created_at", OffsetDateTime.class));
    });

    return proposalSection.stream().toList();
  }

}
