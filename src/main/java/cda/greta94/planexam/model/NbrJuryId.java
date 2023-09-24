package cda.greta94.planexam.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NbrJuryId implements Serializable {

  private Long etabEpreuveId;

  private Long jourId;

  public NbrJuryId(Long etabEpreuveId, Long jourId) {
    this.etabEpreuveId = etabEpreuveId;
    this.jourId = jourId;
  }

  public NbrJuryId() {
  }

  public Long getEtabEpreuveId() {
    return etabEpreuveId;
  }

  public void setEtabEpreuveId(Long sessionEtabId) {
    this.etabEpreuveId = sessionEtabId;
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
    return Objects.equals(etabEpreuveId, nbrJuryId.etabEpreuveId) && Objects.equals(jourId, nbrJuryId.jourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(etabEpreuveId, jourId);
  }
}
