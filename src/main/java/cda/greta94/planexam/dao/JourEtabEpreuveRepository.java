package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.JourEtabEpreuve;
import cda.greta94.planexam.model.JourEtabEpreuveId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourEtabEpreuveRepository extends JpaRepository<JourEtabEpreuve, Long> {
    JourEtabEpreuve findByJourEtabEpreuveId(JourEtabEpreuveId jourEtabEpreuveId);

    JourEtabEpreuve findByJourEtabEpreuveId_EtabEpreuveIdAndJourEtabEpreuveId_JourId(Long etabEpreuveId, Long jourId);


}
