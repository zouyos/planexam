package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourPassageRepository;
import cda.greta94.planexam.dao.SessionRepository;
import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.SessionE5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SessionService {
  private SessionRepository sessionRepository;
  private JourPassageRepository jourPassageRepository;

  public SessionService() {
  }

  @Autowired
  public SessionService(SessionRepository sessionRepository, JourPassageRepository jourPassageRepository) {
    this.sessionRepository = sessionRepository;
    this.jourPassageRepository = jourPassageRepository;
  }

  public List<SessionE5> getAll() {
    return sessionRepository.findAll();
  }

  public SessionE5Dto findSessionDtoById(Long id) {
    SessionE5 sessionE5 = sessionRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new SessionE5Dto(sessionE5.getId(), sessionE5.getLibelle(), sessionE5.getDateDebut(), sessionE5.getDateFin(), (sessionE5.getJourPassages() != null) ? sessionE5.getJourPassages() : null);
  }

  public SessionE5 show(Long id) {
    return sessionRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public void saveSessionFromSessionDto(SessionE5Dto sessionE5Dto) {
    SessionE5 sessionE5 = null;
    if (sessionE5Dto.getId() != null) {
      sessionE5 = sessionRepository.findById(sessionE5Dto.getId()).orElseThrow(NotFoundEntityException::new);
    } else {
     if (sessionE5 == null) sessionE5 = new SessionE5();
    }
    sessionE5.setLibelle(sessionE5Dto.getLibelle());
    sessionE5.setDateDebut((Date) sessionE5Dto.getDateDebut());
    sessionE5.setDateFin((Date) sessionE5Dto.getDateFin());
    // TODO instancier les jourPassages entre dateDebut et dateFin
    sessionRepository.save(sessionE5);
  }

  public SessionE5Dto toDto(SessionE5 sessionE5){
    //TODO
    return new SessionE5Dto();
  }

  public void delete(Long id) {
    sessionRepository.deleteById(id);
  }
}
