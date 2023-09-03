package cda.greta94.planexam.model;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,unique = true)
    @Basic(optional = false)
    private String email;
    @Column(nullable = false)
    @Basic(optional = false)
    private String mdp;

    //Note il est possible d'avoir une association Many to Many entre Utilisateur et Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}

