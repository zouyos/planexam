package cda.greta94.planexam.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NbrJuryId implements Serializable {

  private Long etablissementId;

  private Long jourId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NbrJuryId nbrJuryId)) return false;
    return Objects.equals(etablissementId, nbrJuryId.etablissementId) && Objects.equals(jourId, nbrJuryId.jourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(etablissementId, jourId);
  }
}
