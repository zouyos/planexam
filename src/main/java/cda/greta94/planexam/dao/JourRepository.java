package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Epreuve;
import cda.greta94.planexam.model.Jour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JourRepository extends JpaRepository<Jour, Long> {

  @Transactional
  long deleteByEpreuve(Epreuve epreuve);

  List<Jour> findByEpreuve_Id(Long id);

  @Transactional
  @Modifying
  @Query("update Jour e set e.ouvre = ?1 where e.id = ?2")
  int updateOuvreById(Boolean ouvre, Long id);

  List<Jour> findByJourEtabEpreuveList_JuriesNotNull();
}
