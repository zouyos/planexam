package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourEtabEpreuveRepository;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.EtabEpreuve;
import cda.greta94.planexam.model.JourEtabEpreuve;
import cda.greta94.planexam.model.JourEtabEpreuveId;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EtabEpreuveService {
  private EtabEpreuveRepository etabEpreuveRepository;

  private JourEtabEpreuveRepository jourEtabEpreuveRepository;

  public EtabEpreuveService(EtabEpreuveRepository etabEpreuveRepository) {
    this.etabEpreuveRepository = etabEpreuveRepository;
  }

  public List<EtabEpreuve> getAll(){
    return etabEpreuveRepository.findAll();
  }

  public EtabEpreuve getById(Long id) { return etabEpreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new); }

  public List<EtabEpreuve> getByIdEpreuveAndPonctuel(Long idEpeuve){
    return etabEpreuveRepository.findByEpreuve_IdAndEtablissement_PonctuelTrueOrderByEtablissement_RneAsc(idEpeuve);
  }

  private EtabEpreuve save(EtabEpreuve etabEpreuve){
    return  etabEpreuveRepository.save(etabEpreuve);
  }

  public void createNbrCandidats(Long id, int nbrCandidats) {
    Assert.isTrue(nbrCandidats >= 0, "Le nombre de candidats ne peut pas être négatif");
    EtabEpreuve etabEpreuve = etabEpreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    etabEpreuve.setNbrCandidats(nbrCandidats);
    etabEpreuveRepository.save(etabEpreuve);
  }

  public List<EtabEpreuve> getByJourEtabEpreuveList_JourEtabEpreuveId(Long jourId, Long etabEpreuveId) {
    JourEtabEpreuve jourEtabEpreuve = jourEtabEpreuveRepository.findByJourEtabEpreuveId_EtabEpreuveIdAndJourEtabEpreuveId_JourId(jourId, etabEpreuveId);
    return etabEpreuveRepository.findByJourEtabEpreuveList_Jour_Id(jourId);
  }

  public List<EtabEpreuve> getByJourEtabEpreuveList_Jour_Id(Long jourId){
    return etabEpreuveRepository.findByJourEtabEpreuveList_Jour_Id(jourId);
  }
}
