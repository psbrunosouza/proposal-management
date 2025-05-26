package com.moeb.proposalmanager.proposal.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moeb.proposalmanager.proposal.dto.CreateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.dto.ProposalResponseDTO;
import com.moeb.proposalmanager.proposal.dto.UpdateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.mapper.ProposalMapper;
import com.moeb.proposalmanager.proposal.model.Proposal;
import com.moeb.proposalmanager.proposal.repository.ProposalRepository;
import com.moeb.proposalmanager.shared.exception.ResourceNotFoundException;
import com.moeb.proposalmanager.user.model.User;
import com.moeb.proposalmanager.user.repository.UserRepository;

@Service
@Transactional
public class ProposalService {
  private final ProposalRepository proposalRepository;
  private final UserRepository userRepository;

  public ProposalService(ProposalRepository proposalRepository, UserRepository userRepository) {
    this.proposalRepository = proposalRepository;
    this.userRepository = userRepository;
  }

  public ProposalResponseDTO create(CreateProposalRequestDTO dto) {
    User existingUser = userRepository.findById(dto.getUser().getId())
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Proposal savedProposal = proposalRepository.save(new Proposal(
        null,
        dto.getTitle(),
        existingUser, null));

    return ProposalMapper.toDTO(savedProposal);
  }

  public List<ProposalResponseDTO> findAll() {
    List<Proposal> proposals = proposalRepository.findAll();
    return proposals.stream().map(ProposalMapper::toDTO).toList();
  }

  public Optional<ProposalResponseDTO> findById(UUID id) {
    Optional<Proposal> proposal = proposalRepository.findById(id);
    return proposal.map(ProposalMapper::toDTO);
  }

  public ProposalResponseDTO update(UUID id, UpdateProposalRequestDTO dto) {
    Proposal existingProposal = proposalRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Proposal not found"));

    Proposal savedProposal = proposalRepository.update(new Proposal(
        existingProposal.getId(),
        dto.getTitle(),
        existingProposal.getUser(),
        existingProposal.getCreatedAt()));

    return ProposalMapper.toDTO(savedProposal);
  }

  public void delete(UUID id) {
    proposalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proposal not found"));
    proposalRepository.delete(id);
  }
}
