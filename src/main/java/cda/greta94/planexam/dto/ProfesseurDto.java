package cda.greta94.planexam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfesseurDto {
    private Long id;
    @NotBlank(message = "Prenom est requis")
    private String prenom;
    @NotBlank(message = "Nom est requis")
    private String nom;
    @NotBlank(message = "Email est requis")
    @Email(message = "Email n'est pas valide")
    private String email;

    private Long idVille;
    private Long idEtablissement;
    private Long idSpecialite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public Long getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(Long idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public Long getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Long idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
}
