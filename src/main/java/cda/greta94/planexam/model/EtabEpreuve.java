package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EtabEpreuve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private int nbrCandidats;

  @ManyToOne
  @JoinColumn(name = "epreuve_id")
  private Epreuve epreuve;

  @ManyToOne
  @JoinColumn(name = "etablissement_id")
  private Etablissement etablissement;

  @OneToMany(mappedBy = "etabEpreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<JourEtabEpreuve> jourEtabEpreuveList = new ArrayList<>();

  @OneToMany(mappedBy = "etabEpreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<Jour> jours = new ArrayList<>();

  public JourEtabEpreuve getNbrJury(Jour jour){
    for (JourEtabEpreuve jourEtabEpreuve: this.jourEtabEpreuveList) {
      if(jourEtabEpreuve.getJour().getId() == jour.getId()){
        return jourEtabEpreuve;
      }
    }
    return null;
  }

  public Etablissement getEtablissement() {
    return etablissement;
  }

  public void setEtablissement(Etablissement etablissement) {
    this.etablissement = etablissement;
  }

  public List<JourEtabEpreuve> getJourEtabEpreuveList() {
    return jourEtabEpreuveList;
  }

  public void setJourEtabEpreuveList(List<JourEtabEpreuve> jourEtabEpreuveList) {
    this.jourEtabEpreuveList = jourEtabEpreuveList;
  }

  public Epreuve getEpreuve() {
    return epreuve;
  }

  public void setEpreuve(Epreuve epreuve) {
    this.epreuve = epreuve;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Jour> getJours() {
    return jours;
  }

  public void setJours(List<Jour> jours) {
    this.jours = jours;
  }

  public int getNbrCandidats() {
    return nbrCandidats;
  }

  public void setNbrCandidats(int nbrCandidats) {
    this.nbrCandidats = nbrCandidats;
  }

  public int calcTotalNbrJury() {
    int resultat = 0;
    for (JourEtabEpreuve jourEtabEpreuve: this.jourEtabEpreuveList) {
      resultat += jourEtabEpreuve.getNbrJury();
    }
    return resultat;
  }
}
