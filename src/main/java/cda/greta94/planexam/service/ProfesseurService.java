package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dto.ProfesseurDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Professeur;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class ProfesseurService {
    private ProfesseurRepository professeurRepository;
    private SpecialiteService specialiteService;
    private VilleService villeService;
    private EtablissementService etablissementService;


    @Autowired
    public ProfesseurService(ProfesseurRepository professeurRepository, SpecialiteService specialiteService, VilleService villeService, EtablissementService etablissementService) {
        this.professeurRepository = professeurRepository;
        this.specialiteService = specialiteService;
        this.villeService = villeService;
        this.etablissementService = etablissementService;
    }

    public List<Professeur> getAll(){
        return professeurRepository.findAll();
    }

    public Professeur getById(Long id){
        return professeurRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public ProfesseurDto findProfDtoById(Long id) {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new ProfesseurDto(
          professeur.getId(),
          professeur.getNom(),
          professeur.getPrenom(),
          professeur.getEmail(),
          professeur.getSpecialite().getId(),
          (professeur.getVille() != null) ? professeur.getVille().getId() : null,
          (professeur.getEtablissement() != null) ? professeur.getEtablissement().getId() : null,
          (professeur.getJuryList() != null) ? professeur.getJuryList() : null
        );
    }

    public void saveProfFromDto(ProfesseurDto professeurDto) {
        Professeur professeur = null;
        if (professeurDto.getId() != null) {
            professeur = professeurRepository.findById(professeurDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (professeur == null) professeur = new Professeur();
        }
        professeur.setNom(professeurDto.getNom());
        professeur.setPrenom(professeurDto.getPrenom());
        professeur.setEmail(professeurDto.getEmail());
        professeur.setSpecialite(specialiteService.getById(professeurDto.getIdSpecialite()));
        professeur.setVille(villeService.getById(professeurDto.getIdVille()));
        professeur.setEtablissement(etablissementService.getById(professeurDto.getIdEtablissement()));
        professeurRepository.save(professeur);
    }

    public void delete(Long id) { professeurRepository.deleteById(id); }
}