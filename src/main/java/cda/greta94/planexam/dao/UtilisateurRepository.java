package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);

    Optional<Utilisateur> findByJetonResetMdps_TokenIgnoreCase(String token);
}
