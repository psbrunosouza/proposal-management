package com.moeb.proposalmanager.proposalsection.mapper;

import com.moeb.proposalmanager.proposal.mapper.ProposalMapper;
import com.moeb.proposalmanager.proposalsection.dto.CreateProposalSectionRequestDTO;
import com.moeb.proposalmanager.proposalsection.dto.ProposalSectionResponseDTO;
import com.moeb.proposalmanager.proposalsection.model.ProposalSection;

public class ProposalSectionMapper {
  public static ProposalSection toEntity(CreateProposalSectionRequestDTO dto) {
    return new ProposalSection(
        null,
        dto.getType(),
        ProposalMapper.toEntity(dto.getProposal()),
        dto.getContent(),
        null);
  }

  public static ProposalSectionResponseDTO toDTO(ProposalSection proposalSection) {
    return new ProposalSectionResponseDTO(
        proposalSection.getId(),
        proposalSection.getType(),
        ProposalMapper.toDTO(proposalSection
            .getProposal()),
        proposalSection.getContent(),
        proposalSection.getCreatedAt());
  }
}
