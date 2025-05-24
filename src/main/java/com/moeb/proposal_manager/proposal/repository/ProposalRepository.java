package com.moeb.proposal_manager.proposal.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import com.moeb.proposal_manager.proposal.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
  @EntityGraph(attributePaths = { "user" })
  @NonNull
  @Override
  Optional<Proposal> findById(UUID id);
}
