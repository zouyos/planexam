package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.JourService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ApiController {

    private EtablissementService etablissementService;

    private JourService jourService;

    public ApiController(EtablissementService etablissementService, JourService jourService) {
        this.etablissementService = etablissementService;
        this.jourService = jourService;
    }

    @PostMapping("/etablissement/ponctuel/{id}/{value}")
    public void updatePonctuel(@PathVariable(name = "id") Long id, @PathVariable(name = "value") Boolean value) {
        etablissementService.updatePonctuelById(id, value);
    }

    @PostMapping("/epreuve/jour/{id}/{value}")
    public void changeDispoJour(@PathVariable (name = "id") Long id,@PathVariable(name = "value") Boolean value){
        jourService.updateJourById(id,value);
    }
}
