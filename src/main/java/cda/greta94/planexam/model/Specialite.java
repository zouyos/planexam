package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String libelle;

    @OneToMany(mappedBy = "specialite")
    private List<Professeur> professeurs = new ArrayList<>();

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialite that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLibelle(), that.getLibelle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLibelle());
    }
}
