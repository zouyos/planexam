package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SessionE5 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String libelle;
  private Date dateDebut;
  private Date dateFin;

  @OneToMany(mappedBy = "sessionE5", orphanRemoval = true)
  private List<Jour> jours = new ArrayList<>();

  public List<Jour> getJourPassages() {
    return jours;
  }

  public void setJourPassages(List<Jour> jours) {
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
    if (!(o instanceof SessionE5 sessionE5)) return false;
    return Objects.equals(getId(), sessionE5.getId()) && Objects.equals(getLibelle(), sessionE5.getLibelle());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getLibelle());
  }
}