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
  private int  nbrJuryMax;
  private int nbrCandidats;

  @ManyToOne
  @JoinColumn(name = "epreuve_id")
  private Epreuve epreuve;

  @ManyToOne
  @JoinColumn(name = "etablissement_id")
  private Etablissement etablissement;

  @OneToMany(mappedBy = "etabEpreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<Jury> juryList = new ArrayList<>();

  @OneToMany(mappedBy = "etabEpreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<NbrJury> nbrJuries = new ArrayList<>();

  @OneToMany(mappedBy = "etabEpreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<Jour> jours = new ArrayList<>();

  public NbrJury getNbrJury(Jour jour){
    for (NbrJury jury: this.nbrJuries) {
      if(jury.getJour().getId() == jour.getId()){
        return jury;
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

  public List<NbrJury> getNbrJuries() {
    return nbrJuries;
  }

  public void setNbrJuries(List<NbrJury> nbrJuries) {
    this.nbrJuries = nbrJuries;
  }

  public List<Jury> getJuryList() {
    return juryList;
  }

  public void setJuryList(List<Jury> juryList) {
    this.juryList = juryList;
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
}
