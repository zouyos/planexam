package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.*;
import cda.greta94.planexam.dto.EpreuveDto;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EpreuveService {
  private EpreuveRepository epreuveRepository;
  private JourRepository jourRepository;
  private EtabEpreuveRepository etabEpreuveRepository;
  private VilleService villeService;
  private EtablissementService etablissementService;

  @Autowired
  public EpreuveService(EpreuveRepository epreuveRepository,
                        JourRepository jourRepository,
                        EtabEpreuveRepository etabEpreuveRepository,
                        VilleService villeService,
                        EtablissementService etablissementService)
  {
    this.epreuveRepository = epreuveRepository;
    this.jourRepository = jourRepository;
    this.etabEpreuveRepository = etabEpreuveRepository;
    this.villeService = villeService;
    this.etablissementService = etablissementService;
  }

  public List<Epreuve> getAll() {
    return epreuveRepository.findAll();
  }

  public Epreuve findById(Long id) {
    return epreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public EpreuveDto findEpreuveDtoById(Long id) {
    Epreuve epreuve = epreuveRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new EpreuveDto(epreuve.getId(), epreuve.getLibelle(), epreuve.getDateDebut(), epreuve.getDateFin(), (epreuve.getJours() != null) ? epreuve.getJours() : null, (epreuve.getEtabsEpreuve() != null) ? epreuve.getEtabsEpreuve() : null);
  }

  public void importEtablissementFromCSVFile(MultipartFile file, Long idEpreuve) throws IOException {
    Reader in = new InputStreamReader(file.getInputStream());
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Id", "Nom", "Ville", "RNE", "Code", "Ponctuel").withDelimiter(';').parse(in);
    int nbLigne = 0;
    for (CSVRecord record : records) {
      nbLigne++;
      // saute la première ligne d'entête si elle existe
      if (nbLigne == 1 && record.get("Ville").equals("Ville") && record.get("Nom").equals("Nom")) continue;

      Long idVille = villeService.getOrCreate(record.get("Ville"));

      EtablissementDto etabDto = new EtablissementDto(Long.getLong(record.get("Id")), record.get("Nom"), record.get("RNE"), record.get("Code"), record.get("Ponctuel").startsWith("x") ? true : false, idVille, null);

      // TODO appliquer la validation par injection du Validator (+ vérifier en-tête)

      Etablissement etab = etablissementService.saveEtablissementFromEtablissementDto(etabDto);
      EtabEpreuve etabEpreuve = new EtabEpreuve();
      Epreuve epreuve = epreuveRepository.findById(idEpreuve).orElseThrow();
      etabEpreuve.setEpreuve(epreuve);
      etabEpreuve.setEtablissement(etab);
      etabEpreuveRepository.save(etabEpreuve);
    }
  }

  public void saveEpreuveFromEpreuveDto(EpreuveDto epreuveDto) {
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
    }
    epreuve.setJours(jours);
    epreuveRepository.save(epreuve);
  }

  public List<Date> createJourPassage(Epreuve epreuve) {
    //Pour cela on peut les convertir en LocalDate (ou se servir d'un Calendar)
    List<Date> resultat = new ArrayList<>();
    LocalDate debutLocalDate = epreuve.getDateDebut().toLocalDate();
    LocalDate finLocalDate = epreuve.getDateFin().toLocalDate();

    // d démarre au jour de départ
    LocalDate d = debutLocalDate;
    while(d.compareTo(finLocalDate) <= 0) {
      //on entre dans le if que si d est un jour de la semaine (pas le weekend)
      if (d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY) {
        Date uneDate = Date.valueOf(d);
        resultat.add(uneDate);
      }
      //d passe au jour suivant
      d = d.plusDays(1);
    }
    return resultat;
  }

  public void delete(Long id) {
    epreuveRepository.deleteById(id);
  }
}
