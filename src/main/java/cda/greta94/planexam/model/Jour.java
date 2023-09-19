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
  private Date datePassage;

  private Boolean ouvre = true;

  @ManyToOne
  @JoinColumn(name = "epreuve_id")
  private Epreuve epreuve;

  @OneToMany(mappedBy = "jour")
  private List<NbrJury> nbrJurys = new ArrayList<>(5);

  public Jour() {
  }

  public Jour(Date datePassage, Boolean ouvre, Epreuve epreuve) {
    this.datePassage = datePassage;
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

  public Date getDatePassage() {
    return datePassage;
  }

  public void setDatePassage(Date datePassage) {
    this.datePassage = datePassage;
  }

  public Boolean getOuvre() {
    return ouvre;
  }

  public void setOuvre(Boolean ouvre) {
    this.ouvre = ouvre;
  }

  public List<NbrJury> getNbrJurys() {
    return nbrJurys;
  }

  public void setNbrJurys(List<NbrJury> nbrJurys) {
    this.nbrJurys = nbrJurys;
  }
}
