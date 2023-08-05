package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Professeur;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class SpecialiteDto {
    private Long id;

    @NotBlank(message = "Un libellé est requis")
    private String libelle;

    private List<Professeur> professeurs = new ArrayList<>();

    public SpecialiteDto() {
    }

    public SpecialiteDto(Long id, String libelle, List<Professeur> professeurs) {
        this.id = id;
        this.libelle = libelle;
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

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }
}
