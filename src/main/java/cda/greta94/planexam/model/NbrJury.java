package cda.greta94.planexam.model;

import jakarta.persistence.*;

@Entity
public class NbrJury {
  @EmbeddedId
  private NbrJuryId nbrJuryId;

  private int nbr;

  @MapsId("jourId")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "jour_id")
  private Jour jour;


  @MapsId("etabEpreuveId")
  @ManyToOne
  @JoinColumn(name = "etab_epreuve_id")
  private EtabEpreuve etabEpreuve;

  public EtabEpreuve getEtabEpreuve() {
    return etabEpreuve;
  }

  public void setEtabEpreuve(EtabEpreuve etabEpreuve) {
    this.etabEpreuve = etabEpreuve;
  }

  public NbrJuryId getNbrJuryId() {
    return nbrJuryId;
  }

  public void setNbrJuryId(NbrJuryId nbrJuryId) {
    this.nbrJuryId = nbrJuryId;
  }

  public int getNbr() {
    return nbr;
  }

  public void setNbr(int nbr) {
    this.nbr = nbr;
  }

  public Jour getJour() {
    return jour;
  }

  public void setJour(Jour jour) {
    this.jour = jour;
  }

}
