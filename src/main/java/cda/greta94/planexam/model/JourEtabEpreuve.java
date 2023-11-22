package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class JourEtabEpreuve {
  @EmbeddedId
  private JourEtabEpreuveId jourEtabEpreuveId;

  private int nbrJury;

  @MapsId("jourId")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "jour_id")
  private Jour jour;

  @MapsId("etabEpreuveId")
  @ManyToOne
  @JoinColumn(name = "etab_epreuve_id")
  private EtabEpreuve etabEpreuve;

  @OneToMany(mappedBy = "jourEtabEpreuve", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Jury> juries = new ArrayList<>();



  public List<Jury> getJuries() {
    return juries;
  }

  public void setJuries(List<Jury> juries) {
    this.juries = juries;
  }

  public EtabEpreuve getEtabEpreuve() {
    return etabEpreuve;
  }

  public void setEtabEpreuve(EtabEpreuve etabEpreuve) {
    this.etabEpreuve = etabEpreuve;
  }

  public JourEtabEpreuveId getNbrJuryId() {
    return jourEtabEpreuveId;
  }

  public void setJourEtabEpreuveId(JourEtabEpreuveId jourEtabEpreuveId) {
    this.jourEtabEpreuveId = jourEtabEpreuveId;
  }

  public int getNbrJury() {
    return nbrJury;
  }

  public void setNbrJury(int nbrJury) {
    this.nbrJury = nbrJury;
  }

  public Jour getJour() {
    return jour;
  }

  public void setJour(Jour jour) {
    this.jour = jour;
  }
}
