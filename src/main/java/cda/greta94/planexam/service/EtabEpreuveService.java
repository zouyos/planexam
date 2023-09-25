package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.EtabEpreuve;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtabEpreuveService {
  private EtabEpreuveRepository etabEpreuveRepository;

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
}
