package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtablissementRepository;
import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dao.VilleRepository;
import cda.greta94.planexam.dto.VilleDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

  private VilleRepository villeRepository;
  private EtablissementRepository etablissementRepository;

  private ProfesseurRepository professeurRepository;

  @Autowired
  public VilleService(VilleRepository villeRepository, EtablissementRepository etablissementRepository, ProfesseurRepository professeurRepository) {
    this.villeRepository = villeRepository;
    this.etablissementRepository = etablissementRepository;
    this.professeurRepository = professeurRepository;
  }

  public List<Ville> getAll() { return villeRepository.findAll(); }

  public Long getOrCreate(String nomVille) {
    nomVille = nomVille.isEmpty() ? "N/A": nomVille;
    Ville ville = villeRepository.findByNom(nomVille).orElse(null);
    if (ville == null) {
      ville = new Ville();
      ville.setNom(nomVille);
      villeRepository.save(ville);
    }
    return ville.getId();
  }

  public Ville getById(Long idVille) {
    return villeRepository.findById(idVille).orElseThrow(NotFoundEntityException::new);
  }

  public VilleDto getVilleDtoById(Long idVille) {
    Ville ville = villeRepository.findById(idVille).orElseThrow(NotFoundEntityException::new);
    return new VilleDto(
            ville.getId(),
            ville.getNom(),
            (ville.getEtablissements() != null ? ville.getEtablissements() : null),
            (ville.getProfesseurs() != null ? ville.getProfesseurs() : null)
    );
  }

  public void saveVilleFromDto(VilleDto villeDto) {
    Ville ville = null;
    if (villeDto.getId() != null) {
      ville = villeRepository.findById(villeDto.getId()).orElseThrow(NotFoundEntityException::new);
    } else {
      if (ville == null) {ville = new Ville(); }
    }
    ville.setNom(villeDto.getNom());
    ville.setEtablissements(villeDto.getEtablissements());
    ville.setProfesseurs(villeDto.getProfesseurs());
    villeRepository.save(ville);
  }

  public void delete(Long id) { villeRepository.deleteById(id); }
}
