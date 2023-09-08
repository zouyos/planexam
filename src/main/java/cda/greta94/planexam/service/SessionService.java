package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.SessionRepository;
import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Jour;
import cda.greta94.planexam.model.SessionE5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
  private SessionRepository sessionRepository;
  private JourRepository jourRepository;

  @Autowired
  public SessionService(SessionRepository sessionRepository, JourRepository jourRepository) {
    this.sessionRepository = sessionRepository;
    this.jourRepository = jourRepository;
  }

  public List<SessionE5> getAll() {
    return sessionRepository.findAll();
  }

  public SessionE5 findById(Long id) {
    return sessionRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public SessionE5Dto findSessionDtoById(Long id) {
    SessionE5 sessionE5 = sessionRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new SessionE5Dto(sessionE5.getId(), sessionE5.getLibelle(), sessionE5.getDateDebut(), sessionE5.getDateFin(), (sessionE5.getJourPassages() != null) ? sessionE5.getJourPassages() : null);
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
    List<Jour> jours = new ArrayList<>();
    for (Date uneDate: this.createJourPassage(sessionE5)) {
      Jour jour = new Jour(uneDate, true, sessionE5);
      jours.add(jour);
    }
    sessionE5.setJourPassages(jours);
    sessionRepository.save(sessionE5);
  }

  public List<Date> createJourPassage(SessionE5 sessionE5) {
    //Pour cela on peut les convertir en LocalDate (ou se servir d'un Calendar)
    List<Date> resultat = new ArrayList<>();
    LocalDate debutLocalDate = sessionE5.getDateDebut().toLocalDate();
    LocalDate finLocalDate = sessionE5.getDateFin().toLocalDate();

    // d démare au jour de départ
    LocalDate d = debutLocalDate;
    while(d.compareTo(finLocalDate) <= 0) {
      //on entre dans le if que si d est un jour de la semaine ( pas le weekend)
      if (d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY) {
        Date uneDate= Date.valueOf(d);
        resultat.add(uneDate);
      }
      //d passe au jour suivant
      d = d.plusDays(1);
    }
    return resultat;
  }

  public SessionE5Dto toDto(SessionE5 sessionE5){
    //TODO
    return new SessionE5Dto();
  }

  public void delete(Long id) {
    sessionRepository.deleteById(id);
  }
}
