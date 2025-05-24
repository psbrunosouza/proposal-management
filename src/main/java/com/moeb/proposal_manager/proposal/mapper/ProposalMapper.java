package com.moeb.proposal_manager.proposal.mapper;

import com.moeb.proposal_manager.proposal.dto.ProposalDTO;
import com.moeb.proposal_manager.proposal.model.Proposal;
import com.moeb.proposal_manager.user.mapper.UserMapper;

public class ProposalMapper {
  public static ProposalDTO toDTO(Proposal proposal) {
    if (proposal == null)
      return null;

    return new ProposalDTO(
        proposal.getId(),
        proposal.getTitle(),
        UserMapper.toDTO(proposal
            .getUser()),
        proposal.getCreatedAt());
  }
}