package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.JourEtabEpreuveId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EtabEpreuveRepository extends JpaRepository<EtabEpreuve, Long> {
  List<EtabEpreuve> findByEpreuve_IdAndEtablissement_PonctuelTrueOrderByEtablissement_RneAsc(Long id);

  List<EtabEpreuve> findDistinctByJourEtabEpreuveList_NbrJuryGreaterThan(int nbrJury);

  List<EtabEpreuve> findByJourEtabEpreuveList_Jour_Id(Long id);
}