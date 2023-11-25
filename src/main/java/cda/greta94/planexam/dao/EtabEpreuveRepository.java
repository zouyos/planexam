package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.EtabEpreuve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtabEpreuveRepository extends JpaRepository<EtabEpreuve, Long> {
  List<EtabEpreuve> findByEpreuve_IdAndEtablissement_PonctuelTrueOrderByEtablissement_RneAsc(Long id);

  List<EtabEpreuve> findDistinctByJourEtabEpreuveList_NbrJuryGreaterThan(int nbrJury);
}