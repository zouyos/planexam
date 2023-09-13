package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.EpreuveRepository;
import cda.greta94.planexam.dto.EpreuveDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Epreuve;
import cda.greta94.planexam.model.Jour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EpreuveService {
  private EpreuveRepository epreuveRepository;
  private JourRepository jourRepository;

  @Autowired
  public EpreuveService(EpreuveRepository epreuveRepository, JourRepository jourRepository) {
    this.epreuveRepository = epreuveRepository;
    this.jourRepository = jourRepository;
  }

  public List<Epreuve> getAll() {
    return epreuveRepository.findAll();
  }

  public Epreuve findById(Long id) {
    return epreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public EpreuveDto findEpreuveDtoById(Long id) {
    Epreuve epreuve = epreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new EpreuveDto(epreuve.getId(), epreuve.getLibelle(), epreuve.getDateDebut(), epreuve.getDateFin(), (epreuve.getJours() != null) ? epreuve.getJours() : null);
  }

  public void saveEpreuveFromSessionDto(EpreuveDto epreuveDto) {
    Epreuve epreuve = null;
    if (epreuveDto.getId() != null) {
      epreuve = epreuveRepository.findById(epreuveDto.getId()).orElseThrow(NotFoundEntityException::new);
      jourRepository.deleteByEpreuve(epreuve);
    } else {
     if (epreuve == null) epreuve = new Epreuve();
    }
    epreuve.setLibelle(epreuveDto.getLibelle());
    epreuve.setDateDebut(epreuveDto.getDateDebut());
    epreuve.setDateFin(epreuveDto.getDateFin());
    List<Jour> jours = new ArrayList<>();
    for (Date uneDate: this.createJourPassage(epreuve)) {
      Jour jour = new Jour(uneDate, true, epreuve);
      jours.add(jour);
      System.out.println(uneDate);
    }
    epreuve.setJours(jours);
    epreuveRepository.save(epreuve);
  }

  public List<Date> createJourPassage(Epreuve epreuve) {
    //Pour cela on peut les convertir en LocalDate (ou se servir d'un Calendar)
    List<Date> resultat = new ArrayList<>();
    LocalDate debutLocalDate = epreuve.getDateDebut().toLocalDate();
    LocalDate finLocalDate = epreuve.getDateFin().toLocalDate();

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

  public EpreuveDto toDto(Epreuve epreuve){
    //TODO
    return new EpreuveDto();
  }

  public void delete(Long id) {
    epreuveRepository.deleteById(id);
  }
}
