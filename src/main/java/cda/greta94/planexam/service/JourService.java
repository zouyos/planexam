package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourRepository;
import cda.greta94.planexam.dao.SessionRepository;
import cda.greta94.planexam.dto.JourDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Jour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourService {
    private JourRepository jourRepository;

    private SessionRepository sessionRepository;

    private SessionService sessionService;

    @Autowired
    public JourService(JourRepository jourRepository, SessionRepository sessionRepository, SessionService sessionService) {
        this.jourRepository = jourRepository;
        this.sessionRepository = sessionRepository;
        this.sessionService = sessionService;
    }

    public List<Jour> getAll() { return jourRepository.findAll(); }

    public Jour getById(Long id) {
        return jourRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public JourDto findJourDtoById(Long id) {
        Jour jour = jourRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new JourDto(jour.getId(), jour.getDatePassage(), jour.getSessionE5().getId(), jour.getOuvre());
    }

    public void saveJourFromDto(JourDto jourDto) {
        Jour jour = null;
        if (jourDto.getId() != null) {
            jour = jourRepository.findById(jourDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (jour == null) jour = new Jour();
        }
        jour.setDatePassage(jourDto.getDatePassage());
        jour.setOuvre(jourDto.getOuvre());
        jour.setSessionE5(sessionService.findById(jourDto.getSessionE5Id()));
    }

    public void updateJourById(Boolean value,long id) {
        jourRepository.updateOuvreById(value,id);
    }

    public void delete(Long id) { jourRepository.deleteById(id); }
}
