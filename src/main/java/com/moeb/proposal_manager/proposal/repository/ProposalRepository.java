package com.moeb.proposal_manager.proposal.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.moeb.proposal_manager.proposal.model.Proposal;

public interface ProposalRepository {
  Proposal save(Proposal proposal);

  List<Proposal> findAll();

  Optional<Proposal> findById(UUID id);

  Proposal update(Proposal proposal);

  void delete(UUID id);
}
