package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JuryRepository;
import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dto.JuryDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Jury;
import cda.greta94.planexam.model.Professeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuryService {
    private JuryRepository juryRepository;
    private ProfesseurRepository professeurRepository;

    @Autowired
    public JuryService(JuryRepository juryRepository, ProfesseurRepository professeurRepository) {
        this.juryRepository = juryRepository;
        this.professeurRepository = professeurRepository;
    }

    public List<Jury> getAll() { return juryRepository.findAll(); }

    public Jury getById(Long id) {
        return juryRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public JuryDto getJuryDtoById(Long id) {
        Jury jury = juryRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        return new JuryDto(jury.getId(), jury.getNum(), jury.getProf1(), jury.getProf2());
    }

    public void saveJuryFromDto(JuryDto juryDto) {
        Jury jury = null;
        if (juryDto.getId() != null) {
            jury = juryRepository.findById(juryDto.getId()).orElseThrow(NotFoundEntityException::new);
        } else {
            if (jury == null) jury = new Jury();
        }
        jury.setNum(juryDto.getNum());
        jury.setProf1(juryDto.getProf1());
        jury.setProf2(juryDto.getProf2());
        juryRepository.save(jury);
    }

    public void delete(Long id) {
        Jury jury = juryRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        Professeur prof1 = jury.getProf1();
        Professeur prof2 = jury.getProf2();
        prof1.setJury(null);
        prof2.setJury(null);
        professeurRepository.save(prof1);
        professeurRepository.save(prof2);
        juryRepository.deleteById(id);
    }
}
