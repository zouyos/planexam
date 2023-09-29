package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UtilisateurDto {
    private Long id;
    @Email(message = "format d'email non valide")
    private String email;

    //TODO Ajouter une annotation perso pour vérifier la concordance entre le mdp1 et mdp2
    @NotBlank(message = "veuillez entrer un mot de passe")
    @Size(min = 8)
    private String mdp1NonEncode;

    @NotBlank(message = "veuillez confirmer le mot de passe")
    @Size(min = 8)
    private String mdp2NonEncode;

    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp1NonEncode() {
        return mdp1NonEncode;
    }

    public void setMdp1NonEncode(String mdp1NonEncode) {
        this.mdp1NonEncode = mdp1NonEncode;
    }

    public String getMdp2NonEncode() {
        return mdp2NonEncode;
    }

    public void setMdp2NonEncode(String mdp2NonEncode) {
        this.mdp2NonEncode = mdp2NonEncode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
