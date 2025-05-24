package com.moeb.proposal_manager.proposal.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moeb.proposal_manager.proposal.dto.CreateProposalDTO;
import com.moeb.proposal_manager.proposal.dto.ProposalDTO;
import com.moeb.proposal_manager.proposal.service.ProposalService;
import com.moeb.proposal_manager.shared.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalController {
  private final ProposalService proposalService;

  public ProposalController(ProposalService proposalService) {
    this.proposalService = proposalService;
  }

  @PostMapping
  public ResponseEntity<ProposalDTO> create(@Valid @RequestBody CreateProposalDTO dto) {
    Optional<ProposalDTO> proposal = proposalService.create(dto);
    return proposal.map((u) -> ResponseEntity.status(HttpStatus.CREATED).body(u))
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }
}
