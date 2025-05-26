package com.moeb.proposalmanager.proposal.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeb.proposalmanager.proposal.dto.CreateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.dto.ProposalResponseDTO;
import com.moeb.proposalmanager.proposal.dto.UpdateProposalRequestDTO;
import com.moeb.proposalmanager.proposal.service.ProposalService;
import com.moeb.proposalmanager.shared.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalController {
  private final ProposalService proposalService;

  public ProposalController(ProposalService proposalService) {
    this.proposalService = proposalService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProposalResponseDTO> findById(@PathVariable UUID id) {
    Optional<ProposalResponseDTO> proposal = proposalService.findById(id);
    return proposal.map((p) -> ResponseEntity.ok(p))
        .orElseThrow(() -> new ResourceNotFoundException("Proposal not found"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProposalResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateProposalRequestDTO dto) {
    ProposalResponseDTO proposal = proposalService.update(id, dto);
    return ResponseEntity.ok(proposal);
  }

  @PostMapping
  public ResponseEntity<ProposalResponseDTO> create(@RequestBody CreateProposalRequestDTO dto) {
    ProposalResponseDTO proposal = proposalService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(proposal);
  }

  @GetMapping
  public ResponseEntity<Iterable<ProposalResponseDTO>> findAll() {
    Iterable<ProposalResponseDTO> proposals = proposalService.findAll();
    return ResponseEntity.ok(proposals);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    proposalService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
