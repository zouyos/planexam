package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jour {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private Date dateJ;

  private Boolean ouvre = true;

  @ManyToOne
  @JoinColumn(name = "epreuve_id")
  private Epreuve epreuve;

  @OneToMany(mappedBy = "jour", cascade = CascadeType.ALL)
  private List<JourEtabEpreuve> jourEtabEpreuveList = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "etab_epreuve_id")
  private EtabEpreuve etabEpreuve;

  public Jour() {
  }

  public Jour(Date dateJ, Boolean ouvre, Epreuve epreuve) {
    this.dateJ = dateJ;
    this.ouvre = ouvre;
    this.epreuve = epreuve;
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

  public Date getDateJ() {
    return dateJ;
  }

  public void setDateJ(Date datePassage) {
    this.dateJ = datePassage;
  }

  public Boolean getOuvre() {
    return ouvre;
  }

  public void setOuvre(Boolean ouvre) {
    this.ouvre = ouvre;
  }

  public List<JourEtabEpreuve> getJourEtabEpreuveList() {
    return jourEtabEpreuveList;
  }

  public void setJourEtabEpreuveList(List<JourEtabEpreuve> jourEtabEpreuveList) {
    this.jourEtabEpreuveList = jourEtabEpreuveList;
  }

  public EtabEpreuve getEtabEpreuve() {
    return etabEpreuve;
  }

  public void setEtabEpreuve(EtabEpreuve etabEpreuve) {
    this.etabEpreuve = etabEpreuve;
  }

  public int calcTotalNbrJury() {
    int resultat = 0;
    for (JourEtabEpreuve jourEtabEpreuve: this.jourEtabEpreuveList) {
      resultat += jourEtabEpreuve.getNbrJury();
    }
    return resultat;
  }
}
