package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.JourService;
import cda.greta94.planexam.service.NbrJuryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/**",methods = RequestMethod.GET)
@RequestMapping("/api")
public class ApiController {

    private EtablissementService etablissementService;

    private JourService jourService;

    private NbrJuryService nbrJuryService;

    public ApiController(EtablissementService etablissementService, JourService jourService, NbrJuryService nbrJuryService) {
        this.etablissementService = etablissementService;
        this.jourService = jourService;
        this.nbrJuryService = nbrJuryService;
    }

    @GetMapping("/etablissement/ponctuel/{id}/{value}")
    public void updatePonctuel(@PathVariable(name = "id") Long id, @PathVariable(name = "value") Boolean value) {
        etablissementService.updatePonctuelById(id, value);
    }

    @GetMapping("/nbrJurys/nbr/{id}/{value}")
    public void updateNbrJurys(@PathVariable(name = "id") Long id, @PathVariable(name = "value") int value) {
        nbrJuryService.updateNbrJurysById(id, value);
    }

    @PostMapping("/epreuve/jour/{id}/{value}")
    public void changeDispoJour(@PathVariable (name = "id") Long id,@PathVariable(name = "value") Boolean value){
        jourService.updateJourById(id,value);
    }
}
