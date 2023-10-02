package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.EpreuveRepository;
import cda.greta94.planexam.dto.JourDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Jour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourService {
    private JourRepository jourRepository;

    private EpreuveRepository epreuveRepository;

    private EpreuveService epreuveService;

    @Autowired
    public JourService(JourRepository jourRepository, EpreuveRepository epreuveRepository, EpreuveService epreuveService) {
        this.jourRepository = jourRepository;
        this.epreuveRepository = epreuveRepository;
        this.epreuveService = epreuveService;
    }

    public List<Jour> getAll() { return jourRepository.findAll(); }

    public Jour getById(Long id) {
        return jourRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public JourDto findJourDtoById(Long id) {
        Jour jour = jourRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new JourDto(jour.getId(), jour.getDateJ(), jour.getEpreuve().getId(), jour.getOuvre());
    }

    public List<Jour> findByEpreuve(Long epreuveId){
    return this.jourRepository.findByEpreuve_Id(epreuveId);
    }
    public void saveJourFromDto(JourDto jourDto) {
        Jour jour = null;
        if (jourDto.getId() != null) {
            jour = jourRepository.findById(jourDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (jour == null) jour = new Jour();
        }
        jour.setDateJ(jourDto.getDatePassage());
        jour.setOuvre(jourDto.getOuvre());
        jour.setEpreuve(epreuveService.findById(jourDto.getEpreuveId()));
    }

    public void updateJourById(Long id, Boolean value) {
        jourRepository.updateOuvreById(value,id);
    }

    public void delete(Long id) { jourRepository.deleteById(id); }
}
