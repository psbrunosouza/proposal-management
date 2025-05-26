package com.moeb.proposalmanager.proposalsection.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.moeb.proposalmanager.proposalsection.model.ProposalSection;

public interface ProposalSectionRepository {
  public ProposalSection create(ProposalSection proposalSection);

  public Optional<ProposalSection> findById(UUID id);

  public void delete(UUID id);

  public ProposalSection update(ProposalSection proposalSection);

  public List<ProposalSection> findAll();
}
