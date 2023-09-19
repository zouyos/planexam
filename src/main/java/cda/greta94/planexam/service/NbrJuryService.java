package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.NbrJuryRepository;
import org.springframework.stereotype.Service;

@Service
public class NbrJuryService {

    private NbrJuryRepository nbrJuryRepository;

    public NbrJuryService(NbrJuryRepository nbrJuryRepository) {
        this.nbrJuryRepository = nbrJuryRepository;
    }

    public void updateNbrJurysById(Long id, int value) {
        nbrJuryRepository.updateNbrJurysById(id, value);
    }
}
