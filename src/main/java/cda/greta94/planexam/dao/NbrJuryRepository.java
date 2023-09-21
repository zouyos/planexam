package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.NbrJury;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NbrJuryRepository extends JpaRepository<NbrJury, Long> {

    @Query("SELECT NBR FROM NbrJury NBR WHERE NBR.jour.epreuve.id = ?1 and NBR.etablissement.id = ?2")
    public List<NbrJury> findNbrJuriesByEpreuveAndEtablissement(Long epreuveId, Long etablissementId );

    @Transactional
    @Modifying
    @Query("update NbrJury n set n.nbr = ?1 where n.nbrJuryId = ?2")
    int updateNbrJurysById(Long id, int nbr);
}
