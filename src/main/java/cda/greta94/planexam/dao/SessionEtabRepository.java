package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.SessionEtab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionEtabRepository extends JpaRepository<SessionEtab, Long> {
  List<SessionEtab> findByEtablissement_PonctuelTrueOrderByEtablissement_RneAsc();
}