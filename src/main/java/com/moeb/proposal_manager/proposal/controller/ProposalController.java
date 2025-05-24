package com.moeb.proposal_manager.proposal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moeb.proposal_manager.proposal.dto.CreateProposalDTO;
import com.moeb.proposal_manager.proposal.dto.ProposalDTO;
import com.moeb.proposal_manager.proposal.service.ProposalService;

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalController {
  private final ProposalService proposalService;

  public ProposalController(ProposalService proposalService) {
    this.proposalService = proposalService;
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
