package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.NbrJuryRepository;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.NbrJury;
import cda.greta94.planexam.model.NbrJuryId;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class NbrJuryService {

    private NbrJuryRepository nbrJuryRepository;
    private JourRepository jourRepository;
    private EtabEpreuveRepository etabEpreuveRepository;

    public NbrJuryService(NbrJuryRepository nbrJuryRepository, JourRepository jourRepository, EtabEpreuveRepository etabEpreuveRepository) {
        this.nbrJuryRepository = nbrJuryRepository;
        this.jourRepository = jourRepository;
        this.etabEpreuveRepository = etabEpreuveRepository;
    }

    public void createNbrJuriesById(Long jourId, Long etabEpreuveId, int nbr) {
        Assert.isTrue(nbr >= 0 && nbr <= 5, "Le nombre de jurys ne peut excéder 5");
        NbrJury nbrJury = new NbrJury();
        NbrJuryId nbrJuryId = new NbrJuryId(etabEpreuveId, jourId);
        nbrJury.setNbrJuryId(nbrJuryId);
        nbrJury.setNbr(nbr);

        EtabEpreuve etabEpreuve = etabEpreuveRepository.findById(etabEpreuveId).orElseThrow(NotFoundEntityException::new);
        Jour jour = jourRepository.findById(jourId).orElseThrow(NotFoundEntityException::new);
        nbrJury.setJour(jour);
        nbrJury.setEtabEpreuve(etabEpreuve);
        nbrJuryRepository.save(nbrJury);
    }
}
