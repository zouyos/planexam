package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourPassageRepository;
import cda.greta94.planexam.dao.SessionRepository;
import cda.greta94.planexam.dto.SessionDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Session;
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

  public List<Session> getAll() {
    return sessionRepository.findAll();
  }

  public Session show(Long id) {
    return sessionRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public Session toSession(SessionDto sessionDto) {
    Session session = sessionRepository.findById(sessionDto.getId()).orElse(new Session());
    session.setLibelle(sessionDto.getLibelle());
    session.setDateDebut((Date) sessionDto.getDateDebut());
    session.setDateFin((Date) sessionDto.getDateFin());
    //TODO
    return sessionRepository.save(new Session());
  }

  public SessionDto toDto(Session session){
    //TODO
    return new SessionDto();
  }

  public Session save(SessionDto sessionDto){
    //TODO
    return sessionRepository.save(new Session());
  }
}
