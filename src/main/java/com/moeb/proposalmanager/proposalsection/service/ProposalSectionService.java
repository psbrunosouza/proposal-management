package com.moeb.proposalmanager.proposalsection.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.moeb.proposalmanager.proposal.mapper.ProposalMapper;
import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.proposal.repository.ProposalRepository;
import com.moeb.proposalmanager.proposalsection.dto.CreateProposalSectionRequestDTO;
import com.moeb.proposalmanager.proposalsection.dto.ProposalSectionResponseDTO;
import com.moeb.proposalmanager.proposalsection.dto.UpdateProposalSectionRequestDTO;
import com.moeb.proposalmanager.proposalsection.mapper.ProposalSectionMapper;
import com.moeb.proposalmanager.proposalsection.model.ProposalSection;
import com.moeb.proposalmanager.proposalsection.repository.ProposalSectionRepository;
import com.moeb.proposalmanager.shared.exception.ResourceNotFoundException;

@Service
public class ProposalSectionService {

  private final ProposalSectionRepository proposalSectionRepository;
  private final ProposalRepository proposalRepository;

  public ProposalSectionService(
      ProposalSectionRepository proposalSectionRepository,
      ProposalRepository proposalRepository) {
    this.proposalSectionRepository = proposalSectionRepository;
    this.proposalRepository = proposalRepository;
  }

  public List<ProposalSectionResponseDTO> findAll() {
    return proposalSectionRepository.findAll().stream().map(ProposalSectionMapper::toDTO).toList();
  }

  public ProposalSectionResponseDTO findById(UUID id) {
    return proposalSectionRepository.findById(id).map(ProposalSectionMapper::toDTO).orElseThrow(
        () -> new ResourceNotFoundException("Proposal section not found"));
  }

  public ProposalSectionResponseDTO update(UUID id, UpdateProposalSectionRequestDTO dto) {
    ProposalSection existsProposalSection = proposalSectionRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Proposal section not found"));

    ProposalSection proposalSection = new ProposalSection(
        existsProposalSection.getId(),
        dto.getType(),
        ProposalMapper.toEntity(
            dto.getProposal()),
        dto.getContent(),
        existsProposalSection.getCreatedAt());

    return ProposalSectionMapper.toDTO(proposalSectionRepository.update(proposalSection));
  }

  public void delete(UUID id) {
    proposalSectionRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Proposal section not found"));
    proposalSectionRepository.delete(id);
  }

  public ProposalSectionResponseDTO create(CreateProposalSectionRequestDTO dto) {
    Proposal existsProposal = this.proposalRepository.findById(dto.getProposal().getId())
        .orElseThrow(() -> new ResourceNotFoundException("Proposal not found"));

    ProposalSection proposalSection = new ProposalSection(
        null,
        dto.getType(),
        new Proposal(
            existsProposal.getId(),
            existsProposal.getTitle(),
            existsProposal.getUser(),
            existsProposal.getCreatedAt()),
        dto.getContent(),
        null);

    ProposalSection SavedProposalSection = proposalSectionRepository.create(proposalSection);

    return ProposalSectionMapper.toDTO(SavedProposalSection);
  }
}
