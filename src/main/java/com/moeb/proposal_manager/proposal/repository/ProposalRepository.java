package com.moeb.proposal_manager.proposal.repository;

import com.moeb.proposal_manager.proposal.model.Proposal;

public interface ProposalRepository {
  Proposal save(Proposal proposal);
}
