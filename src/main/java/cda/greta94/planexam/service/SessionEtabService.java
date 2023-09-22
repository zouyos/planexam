package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.SessionEtabRepository;
import cda.greta94.planexam.model.SessionEtab;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionEtabService {
  private SessionEtabRepository sessionEtabRepository;

  public SessionEtabService(SessionEtabRepository sessionEtabRepository) {
    this.sessionEtabRepository = sessionEtabRepository;
  }

  public List<SessionEtab> getAll(){
    return sessionEtabRepository.findAll();
  }

  public List<SessionEtab> getByPonctuel(){
    return sessionEtabRepository.findByEtablissement_PonctuelTrueOrderByEtablissement_RneAsc();
  }

  private  SessionEtab save(SessionEtab sessionEtab){
    return  sessionEtabRepository.save(sessionEtab);
  }
}
