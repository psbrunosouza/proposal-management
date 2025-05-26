package com.moeb.proposalmanager.proposalsection.types;

public enum ProposalSectionType {
  TITLE,
  SUBJECT,
  GOALS,
  METRICS,
  TEAM,
  TIMELINE,
  INVESTMENTS,
  TERM,
  SIGNATURE,
  CONTACTS,
  REFERENCES;

  @Override
  public String toString() {
    return this.name();
  }
}
