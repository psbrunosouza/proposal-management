package com.moeb.proposal_manager.proposal.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moeb.proposal_manager.proposal.dto.CreateProposalDTO;
import com.moeb.proposal_manager.proposal.dto.ProposalDTO;
import com.moeb.proposal_manager.proposal.mapper.ProposalMapper;
import com.moeb.proposal_manager.proposal.model.Proposal;
import com.moeb.proposal_manager.proposal.repository.ProposalRepository;
import com.moeb.proposal_manager.shared.exception.ResourceNotFoundException;
import com.moeb.proposal_manager.user.model.User;
import com.moeb.proposal_manager.user.repository.UserRepository;

@Service
@Transactional
public class ProposalService {
  private final ProposalRepository proposalRepository;
  private final UserRepository userRepository;

  public ProposalService(ProposalRepository proposalRepository, UserRepository userRepository) {
    this.proposalRepository = proposalRepository;
    this.userRepository = userRepository;
  }

  public ProposalDTO create(CreateProposalDTO dto) {
    Optional<User> user = userRepository.findById(dto.getUser().getId());

    if (user.isEmpty()) {
      throw new ResourceNotFoundException("User not found");
    }

    Proposal proposal = new Proposal(dto.getTitle(), user.get());

    Proposal savedProposal = proposalRepository.save(proposal);

    ProposalDTO proposalDTO = ProposalMapper.toDTO(savedProposal);

    return proposalDTO;
  }

  public List<ProposalDTO> findAll() {
    List<Proposal> proposals = proposalRepository.findAll();
    return proposals.stream().map(ProposalMapper::toDTO).toList();
  }
}
