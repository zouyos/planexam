package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UtilisateurDto {
    private Long id;
    @Email(message = "il faut un email valide")
    private String email;
    //TODO Ajouter une annotation perso pour vérifier la concordance entre le mdp1 et mdp2
    @NotBlank(message = "il faut un mdp")
    @Size(min = 8)
    private String mdp1NonEncoder;
    @NotBlank(message = "il faut comfirmer le mdp")
    @Size(min = 8)
    private String mdp2NonEncoder;
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

    public String getMdp1NonEncoder() {
        return mdp1NonEncoder;
    }

    public void setMdp1NonEncoder(String mdp1NonEncoder) {
        this.mdp1NonEncoder = mdp1NonEncoder;
    }

    public String getMdp2NonEncoder() {
        return mdp2NonEncoder;
    }

    public void setMdp2NonEncoder(String mdp2NonEncoder) {
        this.mdp2NonEncoder = mdp2NonEncoder;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
