package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.NbrJury;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NbrJuryRepository extends JpaRepository<NbrJury, Long> {

    @Transactional
    @Modifying
    @Query("update NbrJury n set n.nbr = :nbr where n.jour.id = :jourId and n.etabEpreuve.id = :etabEpreuveId")
    int updateNbrJuryiesById(@Param("jourId") Long jourId, @Param("etabEpreuveId") Long etabEpreuveId, @Param("nbr") int nbr);

    @Transactional
    @Modifying
    @Query("update NbrJury n set n.nbr = ?1 where n.jour.id = ?2 and n.etabEpreuve.id = ?3")
    int updateNbrByJourAndEtabEpreuve(int nbr, Long jourId, Long etabEpreuveId);


}
