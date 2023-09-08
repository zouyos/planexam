package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Etablissement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

  // DSL Domain Specific Language
  Optional<Etablissement> findByRne(String rne);

  Optional<Etablissement> findByNom(String nomVille);

  @Transactional
  @Modifying
  @Query("update Etablissement e set e.ponctuel = ?1 where e.id = ?2")
  int updatePonctuelById(Boolean ponctuel, Long id);
}
