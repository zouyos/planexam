package cda.greta94.planexam.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NbrJuryId implements Serializable {

  private Long sessionEtabId;

  private Long jourId;

  public NbrJuryId(Long sessionEtabId, Long jourId) {
    this.sessionEtabId = sessionEtabId;
    this.jourId = jourId;
  }

  public NbrJuryId() {
  }

  public Long getSessionEtabId() {
    return sessionEtabId;
  }

  public void setSessionEtabId(Long sessionEtabId) {
    this.sessionEtabId = sessionEtabId;
  }

  public Long getJourId() {
    return jourId;
  }

  public void setJourId(Long jourId) {
    this.jourId = jourId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NbrJuryId nbrJuryId)) return false;
    return Objects.equals(sessionEtabId, nbrJuryId.sessionEtabId) && Objects.equals(jourId, nbrJuryId.jourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionEtabId, jourId);
  }
}
