package cda.greta94.planexam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfesseurDto {

    public ProfesseurDto() {
    }

    public ProfesseurDto(Long id, String nom, String prenom, String email, Long idVille, Long idEtablissement, Long idSpecialite, Long idJury) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idSpecialite = idSpecialite;
        this.idVille = idVille;
        this.idEtablissement = idEtablissement;
        this.idJury = idJury;
    }

    private Long id;
    @NotBlank(message = "Nom est requis")
    private String nom;
    @NotBlank(message = "Prenom est requis")
    private String prenom;
    @NotBlank(message = "Email est requis")
    @Email(message = "Email n'est pas valide")
    private String email;

    private Long idSpecialite;

    private Long idVille;

    private Long idEtablissement;

    private Long idJury;

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

    public Long getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Long idSpecialite) {
        this.idSpecialite = idSpecialite;
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
}
