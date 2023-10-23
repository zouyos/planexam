package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.JourEtabEpreuve;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EpreuveDto {
  private Long id;

  @NotBlank(message = "Un libellé est requis")
  private String libelle;

  @NotNull
  private Date dateDebut;

  @NotNull
  private Date dateFin;

  private List<Jour> jours = new ArrayList<>();

  private List<EtabEpreuve> etabsEpreuve = new ArrayList<>();


  public EpreuveDto() {
  }

  public EpreuveDto(Long id, String libelle, @NotNull Date dateDebut, @NotNull Date dateFin, List<Jour> jours, List<EtabEpreuve> etabsEpreuve) {
    this.id = id;
    this.libelle = libelle;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.jours = jours;
    this.etabsEpreuve = etabsEpreuve;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

  public int calcTotalNbrJury() {
    int resultat = 0;
    for (Jour jour: this.jours) {
      resultat += jour.calcTotalNbrJury();
    }
    for (EtabEpreuve etabEpreuve: this.etabsEpreuve) {
      resultat += etabEpreuve.calcTotalNbrJury();
    }
    return resultat/2;
  }
}
