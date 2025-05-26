package com.moeb.proposalmanager.proposalsection.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.moeb.proposalmanager.proposalsection.dto.CreateProposalSectionRequestDTO;
import com.moeb.proposalmanager.proposalsection.dto.ProposalSectionResponseDTO;
import com.moeb.proposalmanager.proposalsection.dto.UpdateProposalSectionRequestDTO;
import com.moeb.proposalmanager.proposalsection.service.ProposalSectionService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/proposal-section")
public class ProposalSectionController {

  private final ProposalSectionService proposalSectionService;

  public ProposalSectionController(ProposalSectionService proposalSectionService) {
    this.proposalSectionService = proposalSectionService;
  }

  @PostMapping
  public ResponseEntity<ProposalSectionResponseDTO> create(
      @RequestBody CreateProposalSectionRequestDTO proposalSection) {
    return ResponseEntity.status(HttpStatus.CREATED).body(proposalSectionService.create(proposalSection));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProposalSectionResponseDTO> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(proposalSectionService.findById(id));
  }

  @GetMapping
  public ResponseEntity<Iterable<ProposalSectionResponseDTO>> findAll() {
    return ResponseEntity.ok(proposalSectionService.findAll());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    proposalSectionService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProposalSectionResponseDTO> update(
      @PathVariable UUID id,
      @RequestBody UpdateProposalSectionRequestDTO proposalSection) {
    return ResponseEntity.ok(proposalSectionService.update(id, proposalSection));
  }
}
