package com.moeb.proposal_manager.proposal.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moeb.proposal_manager.proposal.dto.CreateProposalDTO;
import com.moeb.proposal_manager.proposal.dto.ProposalDTO;
import com.moeb.proposal_manager.proposal.dto.UpdateProposalDTO;
import com.moeb.proposal_manager.proposal.service.ProposalService;
import com.moeb.proposal_manager.shared.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalController {
  private final ProposalService proposalService;

  public ProposalController(ProposalService proposalService) {
    this.proposalService = proposalService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProposalDTO> findById(@PathVariable UUID id) {
    Optional<ProposalDTO> proposal = proposalService.findById(id);
    return proposal.map((p) -> ResponseEntity.ok(p))
        .orElseThrow(() -> new ResourceNotFoundException("Proposal not found"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProposalDTO> update(@PathVariable UUID id, @RequestBody UpdateProposalDTO dto) {
    ProposalDTO proposal = proposalService.update(id, dto);
    return ResponseEntity.ok(proposal);
  }

  @PostMapping
  public ResponseEntity<ProposalDTO> create(@RequestBody CreateProposalDTO dto) {
    ProposalDTO proposal = proposalService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(proposal);
  }

  @GetMapping
  public ResponseEntity<Iterable<ProposalDTO>> findAll() {
    Iterable<ProposalDTO> proposals = proposalService.findAll();
    return ResponseEntity.ok(proposals);
  }
}
