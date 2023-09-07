package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourPassageRepository;
import cda.greta94.planexam.dao.SessionRepository;
import cda.greta94.planexam.dto.JourPassageDto;
import cda.greta94.planexam.dto.ProfesseurDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.JourPassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourPassageService {
    private JourPassageRepository jourPassageRepository;

    private SessionRepository sessionRepository;

    private SessionService sessionService;

    @Autowired
    public JourPassageService(JourPassageRepository jourPassageRepository, SessionRepository sessionRepository, SessionService sessionService) {
        this.jourPassageRepository = jourPassageRepository;
        this.sessionRepository = sessionRepository;
        this.sessionService = sessionService;
    }

    public List<JourPassage> getAll() { return jourPassageRepository.findAll(); }

    public JourPassage getById(Long id) {
        return jourPassageRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public JourPassageDto findJourPassageDtoById(Long id) {
        JourPassage jourPassage = jourPassageRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new JourPassageDto(jourPassage.getId(), jourPassage.getDatePassage(), jourPassage.getSessionE5().getId(), jourPassage.getOuvré());
    }

    public void saveJourPassageFromDto(JourPassageDto jourPassageDto) {
        JourPassage jourPassage = null;
        if (jourPassageDto.getId() != null) {
            jourPassage = jourPassageRepository.findById(jourPassageDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (jourPassage == null) jourPassage = new JourPassage();
        }
        jourPassage.setDatePassage(jourPassageDto.getDatePassage());
        jourPassage.setOuvré(jourPassageDto.getOuvré());
        jourPassage.setSessionE5(sessionService.findById(jourPassageDto.getSessionE5Id()));
    }

    public void delete(Long id) { jourPassageRepository.deleteById(id); }
}
