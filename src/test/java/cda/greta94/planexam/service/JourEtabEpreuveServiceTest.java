package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourEtabEpreuveRepository;
import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.JourEtabEpreuve;
import cda.greta94.planexam.model.JourEtabEpreuveId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JourEtabEpreuveServiceTest {
    @Autowired
    private JourEtabEpreuveService jourEtabEpreuveService;

    @Autowired
    private JourEtabEpreuveRepository jourEtabEpreuveRepository;

    @Autowired
    private EtabEpreuveRepository etabEpreuveRepository;

    @Autowired
    private JourRepository jourRepository;

    @Test
    public void testCreateNbrJuriesById() {
        // Données de test
        Long jourId = 1L;
        Long etabEpreuveId = 2L;
        int nbrJury = 4;

        // Appeler la méthode testée
        jourEtabEpreuveService.createNbrJuriesById(jourId, etabEpreuveId, nbrJury);

        // Vérifier le résultat en utilisant des assertions
        JourEtabEpreuve jourEtabEpreuve = jourEtabEpreuveRepository
                .findByJourEtabEpreuveId(new JourEtabEpreuveId(etabEpreuveId, jourId));
        assertNotNull(jourEtabEpreuve, "Le jourEtabEpreuve ne doit pas être null");
        assertEquals(nbrJury, jourEtabEpreuve.getNbrJury(), "Le nombre de jury doit être conforme");

        EtabEpreuve etabEpreuve = etabEpreuveRepository.findById(etabEpreuveId).orElse(null);
        assertNotNull(etabEpreuve, "L'EtabEpreuve ne doit pas être null");

        Jour jour = jourRepository.findById(jourId).orElse(null);
        assertNotNull(jour, "Le Jour ne doit pas être null");
    }
}