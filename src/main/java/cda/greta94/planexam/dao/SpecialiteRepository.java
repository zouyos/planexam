package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

    Optional<Specialite> findByLibelle(String libelleSpec);
}