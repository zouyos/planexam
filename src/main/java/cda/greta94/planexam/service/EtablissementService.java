package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.*;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Epreuve;
import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.model.SessionEtab;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Component()
public class EtablissementService {
  private Logger logger = LoggerFactory.getLogger(EtablissementService.class);

  private EtablissementRepository etablissementRepository;
  private VilleService villeService;
  private VilleRepository villeRepository;
  private NbrJuryRepository nbrJuryRepository;

  private SessionEtabRepository sessionEtabRepository;
  private final EpreuveRepository epreuveRepository;



  public EtablissementService(EtablissementRepository etablissementRepository, VilleService villeService, VilleRepository villeRepository, NbrJuryRepository nbrJuryRepository, SessionEtabRepository sessionEtabRepository,
                              EpreuveRepository epreuveRepository) {
    this.etablissementRepository = etablissementRepository;
    this.villeService = villeService;
    this.villeRepository = villeRepository;
    this.nbrJuryRepository = nbrJuryRepository;
    this.sessionEtabRepository = sessionEtabRepository;
    this.epreuveRepository = epreuveRepository;
  }

  public Etablissement saveEtablissementFromEtablissementDto(EtablissementDto etablissementDto) {
    Etablissement etablissement = null;
    if ((etablissementDto.getId() != null)) {
      etablissement = etablissementRepository.findById(etablissementDto.getId()).orElseThrow(NotFoundEntityException::new);
    } else {
      if ((etablissementDto.getRne() != null)) {
        etablissement = etablissementRepository.findByRne(etablissementDto.getRne()).orElse(null);
      }
      if (etablissement == null) etablissement = new Etablissement();
    }
    etablissement.setNom(etablissementDto.getNom());
    etablissement.setPonctuel(etablissementDto.getPonctuel());
    etablissement.setRne(etablissementDto.getRne());
    etablissement.setCode(etablissementDto.getCode());
    etablissement.setVille(villeService.getById(etablissementDto.getIdVille()));
    etablissement.setProfesseurs(etablissementDto.getProfesseurs());

    return etablissementRepository.save(etablissement);
  }

  public List<Etablissement> getAll() {
    return etablissementRepository.findAll();
  }

  public Page<Etablissement> getAllPage(Pageable pageable) {
    return etablissementRepository.findAll(pageable);
  }

  public Page<Etablissement> getPageEtrecherche(String nom, Pageable pageable) {
    return etablissementRepository.findByNomContainsIgnoreCaseOrderByRneAsc(nom, pageable);
  }

  public EtablissementDto findEtablissementDtoById(long id) {
    Etablissement etab = etablissementRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new EtablissementDto(etab.getId(), etab.getNom(), etab.getRne(), etab.getCode(),etab.getPonctuel(), (etab.getVille() != null) ? etab.getVille().getId() : null, (etab.getProfesseurs() != null) ? etab.getProfesseurs() : null);
  }

  public Etablissement getById(Long id) {
    return etablissementRepository.findById(id).orElseThrow(NotFoundEntityException::new);
  }

  public Optional<Etablissement> findByNom(String nomVille) {
    return etablissementRepository.findByNom(nomVille);
  }

  public List<Etablissement> getByPonctuel(Boolean ponctuel) {
    return etablissementRepository.findByPonctuel(ponctuel);
  }


  public void importEtablissementFromCSVFile(MultipartFile file, Long idSession) throws IOException {
    Reader in = new InputStreamReader(file.getInputStream());
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Id", "Nom", "Ville", "RNE", "Code", "Ponctuel").withDelimiter(';').parse(in);
    // Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
    // Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().withSkipHeaderRecord().parse(in);
    int nbLigne = 0;
    for (CSVRecord record : records) {
      nbLigne++;
      // saute la première ligne d'entête si elle existe
      if (nbLigne == 1 && record.get("Ville").equals("Ville") && record.get("Nom").equals("Nom")) continue;

      Long idVille = villeService.getOrCreate(record.get("Ville"));

      EtablissementDto etabDto = new EtablissementDto(null, record.get("Nom"), record.get("RNE"), record.get("Code"), record.get("Ponctuel").startsWith("x") ? true : false, idVille, null);

      // TODO appliquer la validation par injection du Validator

      Etablissement etab = this.saveEtablissementFromEtablissementDto(etabDto);
      SessionEtab sessionEtab = new SessionEtab();
      Epreuve epreuve = epreuveRepository.findById(idSession).orElseThrow();
      sessionEtab.setEpreuve(epreuve);
      sessionEtab.setEtablissement(etab);
      sessionEtabRepository.save(sessionEtab);
    }
  }

  public Long getOrCreate(String rne) {
    Etablissement etab = etablissementRepository.findByRne(rne).orElse(null);
    if (etab == null) {
      etab = new Etablissement();
      etab.setRne(rne);
      //TODO
      etab.setNom("TODO");
      etab.setCode("TODO");
      etab.setPonctuel(false);
      etab.setVille(villeRepository.findByNom("N/A").orElse(null));
      etablissementRepository.save(etab);
    }
    return etab.getId();
  }

  public void updatePonctuelById(Long id, Boolean value) {
    etablissementRepository.updatePonctuelById(value,id);
  }

  public void delete(Long id) {
    etablissementRepository.deleteById(id);
  }
}
