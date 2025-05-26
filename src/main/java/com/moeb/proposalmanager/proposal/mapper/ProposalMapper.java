package com.moeb.proposalmanager.proposal.mapper;

import com.moeb.proposalmanager.proposal.dto.CreateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.dto.ProposalResponseDTO;
import com.moeb.proposalmanager.proposal.dto.UpdateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.user.mapper.UserMapper;

public class ProposalMapper {
  public static ProposalResponseDTO toDTO(Proposal proposal) {
    if (proposal == null)
      return null;

    return new ProposalResponseDTO(
        proposal.getId(),
        proposal.getTitle(),
        UserMapper.toDTO(proposal
            .getUser()),
        proposal.getCreatedAt());
  }

  public static Proposal toEntity(UpdateProposalRequestDTO dto) {
    if (dto == null)
      return null;

    return new Proposal(
        null,
        dto.getTitle(),
        null,
        null);
  }

  public static Proposal toEntity(CreateProposalRequestDTO dto) {
    if (dto == null)
      return null;

    return new Proposal(
        null,
        dto.getTitle(),
        UserMapper.toEntity(dto
            .getUser()),
        null);
  }

  public static Proposal toEntity(ProposalResponseDTO dto) {
    if (dto == null)
      return null;

    return new Proposal(
        dto.getId(),
        dto.getTitle(),
        UserMapper.toEntity(dto
            .getUser()),
        dto.getCreatedAt());

  }
}