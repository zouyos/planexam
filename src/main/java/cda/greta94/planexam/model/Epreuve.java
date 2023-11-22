package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Epreuve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private String libelle;
  private Date dateDebut;
  private Date dateFin;

  @OneToMany(mappedBy = "epreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<Jour> jours = new ArrayList<>();

  @OneToMany(mappedBy = "epreuve", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<EtabEpreuve> etabsEpreuve = new ArrayList<>();

  public List<EtabEpreuve> getEtabsEpreuve() {
    return etabsEpreuve;
  }

  public void setEtabsEpreuve(List<EtabEpreuve> etabsEpreuve) {
    this.etabsEpreuve = etabsEpreuve;
  }

  public List<Jour> getJours() {
    return jours;
  }

  public void setJours(List<Jour> jours) {
    this.jours = jours;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Epreuve epreuve)) return false;
    return Objects.equals(getId(), epreuve.getId()) && Objects.equals(getLibelle(), epreuve.getLibelle());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getLibelle());
  }
}