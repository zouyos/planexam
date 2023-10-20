package cda.greta94.planexam.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JourEtabEpreuveId implements Serializable {

  private Long etabEpreuveId;

  private Long jourId;

  public JourEtabEpreuveId(Long etabEpreuveId, Long jourId) {
    this.etabEpreuveId = etabEpreuveId;
    this.jourId = jourId;
  }

  public JourEtabEpreuveId() {
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
    if (!(o instanceof JourEtabEpreuveId jourEtabEpreuveId)) return false;
    return Objects.equals(etabEpreuveId, jourEtabEpreuveId.etabEpreuveId) && Objects.equals(jourId, jourEtabEpreuveId.jourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(etabEpreuveId, jourId);
  }
}
