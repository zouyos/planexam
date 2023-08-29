package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dao.SpecialiteRepository;
import cda.greta94.planexam.dto.SpecialiteDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Specialite;
import cda.greta94.planexam.model.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialiteService {
    private SpecialiteRepository specialiteRepository;

    private ProfesseurRepository professeurRepository;

    @Autowired
    public SpecialiteService(SpecialiteRepository specialiteRepository, ProfesseurRepository professeurRepository) {
        this.specialiteRepository = specialiteRepository;
        this.professeurRepository = professeurRepository;
    }

    public List<Specialite> getAll() { return specialiteRepository.findAll(); }

    public Specialite getById(Long id) {
        return specialiteRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public SpecialiteDto findSpecialiteDtoById(Long id) {
        Specialite specialite = specialiteRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new SpecialiteDto((specialite.getId()), specialite.getLibelle(), (specialite.getProfesseurs() != null ? specialite.getProfesseurs() : null));
    }

    public Long getOrCreate(String libelle) {
        //TODO virer spec autre
        libelle = libelle.isEmpty() ? "Autre": libelle;
        Specialite spec = specialiteRepository.findByLibelle(libelle).orElse(null);
        if (spec == null) {
            spec = new Specialite();
            spec.setLibelle(libelle);
            specialiteRepository.save(spec);
        }
        return spec.getId();
    }

    public void saveSpecialiteFromDto(SpecialiteDto specialiteDto) {
        Specialite specialite = null;
        if (specialiteDto.getId() != null) {
            specialite = specialiteRepository.findById(specialiteDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (specialite == null) specialite = new Specialite();
        }
        specialite.setLibelle(specialiteDto.getLibelle());
        specialite.setProfesseurs(specialiteDto.getProfesseurs());
        specialiteRepository.save(specialite);
    }

    public void delete(Long id) {
        specialiteRepository.deleteById(id);
    }
}
