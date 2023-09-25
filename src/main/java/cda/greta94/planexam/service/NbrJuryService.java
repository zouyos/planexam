package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.NbrJuryRepository;
import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.NbrJury;
import cda.greta94.planexam.model.NbrJuryId;
import org.springframework.stereotype.Service;

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

    public void updateNbrJuriesById(Long jourId, Long etabEpreuveId, int nbr) {
        NbrJuryId nbrJuryId = new NbrJuryId(etabEpreuveId,jourId);
        NbrJury nbrJury= new NbrJury();
        nbrJury.setNbrJuryId(nbrJuryId);
        nbrJury.setNbr(nbr);

        EtabEpreuve etabEpreuve = etabEpreuveRepository.findById(etabEpreuveId).orElseThrow();
        Jour jour = jourRepository.findById(jourId).orElseThrow();
        nbrJury.setJour(jour);
        nbrJury.setEtabEpreuve(etabEpreuve);
        nbrJuryRepository.save(nbrJury);
        //nbrJuryRepository.updateNbrJuryiesById(jourId, etabEpreuveId, nbr);
    }
}
