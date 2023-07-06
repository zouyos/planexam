package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.JourPassage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SessionE5Dto {
  private Long id;

  @NotBlank(message = "Un libellé est requis")
  private String libelle;

  @NotNull
  private Date dateDebut;

  @NotNull
  private Date dateFin;

  private List<JourPassage> jourPassages = new ArrayList<>();

  public SessionE5Dto() {
  }

  public SessionE5Dto(Long id, String libelle, @NotNull Date dateDebut, @NotNull Date dateFin, List<JourPassage> jourPassages) {
    this.id = id;
    this.libelle = libelle;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.jourPassages = jourPassages;
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
}
