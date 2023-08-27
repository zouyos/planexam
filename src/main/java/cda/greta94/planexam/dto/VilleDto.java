package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.model.Professeur;
import cda.greta94.planexam.model.Ville;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class VilleDto {
    private Long id;

    @NotBlank(message = "Un nom est requis")
    private String nom;

    private List<Etablissement> etablissements = new ArrayList<>();

    private List<Professeur> professeurs = new ArrayList<>();

    public VilleDto() {
    }

    public VilleDto(Long id, String nom, List<Etablissement> etablissements, List<Professeur> professeurs) {
        this.id = id;
        this.nom = nom;
        this.etablissements = etablissements;
        this.professeurs = professeurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }

}
