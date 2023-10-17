package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.EtabEpreuve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtabEpreuveRepository extends JpaRepository<EtabEpreuve, Long> {
  List<EtabEpreuve> findByEtablissement_PonctuelTrueOrderByEtablissement_RneAsc();

  List<EtabEpreuve> findByEpreuve_IdAndEtablissement_PonctuelTrueOrderByEtablissement_RneAsc(Long id);
}