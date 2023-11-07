package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    List<Professeur> findBySpecialite_Libelle(String libelle);

    List<Professeur> findBySpecialite_IdGreaterThan(Long id);
}