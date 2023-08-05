package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dto.ProfesseurDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Professeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {
    private ProfesseurRepository professeurRepository;

    //Constructeur vide ?

    @Autowired
    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public List<Professeur> getAll(){
        return professeurRepository.findAll();
    }

    public Professeur findById(Long id){
        return professeurRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public ProfesseurDto findProfDtoById(Long id) {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new ProfesseurDto(
          professeur.getId(),
          professeur.getNom(),
          professeur.getPrenom(),
          professeur.getEmail(),
          (professeur.getVille() != null) ? professeur.getVille().getId() : null,
          (professeur.getEtablissement() != null) ? professeur.getEtablissement().getId() : null,
          (professeur.getSpecialite() != null) ? professeur.getSpecialite().getId() : null
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
        //TODO instancier Ville/Etab/Spec
        professeurRepository.save(professeur);
    }

    public ProfesseurDto toDto(Professeur professeur){
        //TODO
        return new ProfesseurDto();
    }

    public void delete(Long id) { professeurRepository.deleteById(id); }
}