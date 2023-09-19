package cda.greta94.planexam.model;

import jakarta.persistence.*;

@Entity
public class NbrJury {
  @EmbeddedId
  private NbrJuryId nbrJuryId;

  private int nbr = 0;

  @MapsId("jourId")
  @ManyToOne
  @JoinColumn(name = "jour_id")
  private Jour jour;

  @MapsId("etablissementId")
  @ManyToOne
  @JoinColumn(name = "etablissement_id")
  private Etablissement etablissement;
}
