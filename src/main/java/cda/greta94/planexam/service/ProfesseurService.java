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
          professeur.getSpecialite().getId(),
          (professeur.getVille() != null) ? professeur.getVille().getId() : null,
          (professeur.getEtablissement() != null) ? professeur.getEtablissement().getId() : null
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
        professeurRepository.save(professeur);
    }

    public void importProfFromCSV(MultipartFile file) throws IOException {
        Reader in = new InputStreamReader(file.getInputStream());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("id", "Prénom", "Nom", "Email", "Ville", "RNE", "Spécialité").withDelimiter(';').parse(in);

        int nbLigne = 0;
        for (CSVRecord record : records) {
            nbLigne++;
            if(nbLigne == 1 && record.get("Prénom").equals("Prénom") && record.get("Nom").equals("Nom")) continue;

            Long idVille = villeService.getOrCreate(record.get("Ville"));
            Long idEtab = etablissementService.getOrCreate(record.get("RNE"));
            Long idSpec = specialiteService.getOrCreate(record.get("Spécialité"));

            ProfesseurDto profDto = new ProfesseurDto(null, record.get("Prénom"), record.get("Nom"), record.get("Email"), idVille, idEtab, idSpec);

            this.saveProfFromDto(profDto);
        }
    }

    public ProfesseurDto toDto(Professeur professeur){
        //TODO
        return new ProfesseurDto();
    }

    public void delete(Long id) { professeurRepository.deleteById(id); }
}