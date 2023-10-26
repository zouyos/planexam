package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.JourEtabEpreuveRepository;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.JourEtabEpreuve;
import cda.greta94.planexam.model.JourEtabEpreuveId;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class JourEtabEpreuveService {

    private JourEtabEpreuveRepository jourEtabEpreuveRepository;
    private JourRepository jourRepository;
    private EtabEpreuveRepository etabEpreuveRepository;

    public JourEtabEpreuveService(JourEtabEpreuveRepository jourEtabEpreuveRepository, JourRepository jourRepository, EtabEpreuveRepository etabEpreuveRepository) {
        this.jourEtabEpreuveRepository = jourEtabEpreuveRepository;
        this.jourRepository = jourRepository;
        this.etabEpreuveRepository = etabEpreuveRepository;
    }

    public void createNbrJuriesById(Long jourId, Long etabEpreuveId, int nbrJury) {
        Assert.isTrue(nbrJury >= 0, "Le nombre de jurys ne peut pas être négatif");
        JourEtabEpreuve jourEtabEpreuve = new JourEtabEpreuve();
        JourEtabEpreuveId jourEtabEpreuveId = new JourEtabEpreuveId(etabEpreuveId, jourId);
        jourEtabEpreuve.setNbrJuryId(jourEtabEpreuveId);
        jourEtabEpreuve.setNbrJury(nbrJury);

        EtabEpreuve etabEpreuve = etabEpreuveRepository.findById(etabEpreuveId).orElseThrow(NotFoundEntityException::new);
        Jour jour = jourRepository.findById(jourId).orElseThrow(NotFoundEntityException::new);
        jourEtabEpreuve.setJour(jour);
        jourEtabEpreuve.setEtabEpreuve(etabEpreuve);
        jourEtabEpreuveRepository.save(jourEtabEpreuve);
    }
}
