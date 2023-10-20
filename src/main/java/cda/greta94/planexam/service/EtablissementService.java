package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.*;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Etablissement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {
  private Logger logger = LoggerFactory.getLogger(EtablissementService.class);

  private EtablissementRepository etablissementRepository;
  private VilleService villeService;
  private VilleRepository villeRepository;
  private JourEtabEpreuveRepository jourEtabEpreuveRepository;
  private EtabEpreuveRepository etabEpreuveRepository;
  private final EpreuveRepository epreuveRepository;

  public EtablissementService(EtablissementRepository etablissementRepository, VilleService villeService, VilleRepository villeRepository, JourEtabEpreuveRepository jourEtabEpreuveRepository, EtabEpreuveRepository etabEpreuveRepository,
                              EpreuveRepository epreuveRepository) {
    this.etablissementRepository = etablissementRepository;
    this.villeService = villeService;
    this.villeRepository = villeRepository;
    this.jourEtabEpreuveRepository = jourEtabEpreuveRepository;
    this.etabEpreuveRepository = etabEpreuveRepository;
    this.epreuveRepository = epreuveRepository;
  }

  public Etablissement saveEtablissementFromEtablissementDto(EtablissementDto etablissementDto) {
    Etablissement etablissement = etablissementRepository.findByRne(etablissementDto.getRne()).orElse(new Etablissement());
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

  public Long getOrCreate(String rne) {
    rne = rne.isEmpty() ? "N/A": rne;
    Etablissement etab = etablissementRepository.findByRne(rne).orElse(null);
    if (etab == null) {
      etab = new Etablissement();
      etab.setRne(rne);
      //TODO: Utiliser API qui retrouve le nom lycée par RNE
      etab.setNom("TODO");
      //TODO: Transformer etab.nom en codifié
      etab.setCode("TODO");
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
