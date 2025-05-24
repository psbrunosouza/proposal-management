package com.moeb.proposal_manager.proposal.repository;

import java.util.List;

import com.moeb.proposal_manager.proposal.model.Proposal;

public interface ProposalRepository {
  Proposal save(Proposal proposal);

  List<Proposal> findAll();
}
