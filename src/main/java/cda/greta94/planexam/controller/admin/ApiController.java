package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.AuthRequestDto;
import cda.greta94.planexam.dto.AuthResponseDto;
import cda.greta94.planexam.service.AuthService;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.JourService;
import cda.greta94.planexam.service.JourEtabEpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/**", methods = {RequestMethod.GET, RequestMethod.POST})
public class ApiController {

    private AuthService authService;

    private EtablissementService etablissementService;

    private JourService jourService;

    private JourEtabEpreuveService jourEtabEpreuveService;

    public ApiController(
        EtablissementService etablissementService,
        JourService jourService,
        JourEtabEpreuveService jourEtabEpreuveService,
        AuthService authService
    ) {
        this.etablissementService = etablissementService;
        this.jourService = jourService;
        this.jourEtabEpreuveService = jourEtabEpreuveService;
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto dto) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }

    @PostMapping("/api/etablissement/ponctuel/{id}/{value}")
    public void updatePonctuel(@PathVariable(name = "id") Long id, @PathVariable(name = "value") Boolean value) {
        etablissementService.updatePonctuelById(id, value);
    }

    @PostMapping("/api/epreuve/nbr-juries/{jourId}/{etabEpreuveId}/{nbr}")
    public void updateNbrJurys(@PathVariable(name = "jourId") Long jourId,
                               @PathVariable(name = "etabEpreuveId") Long etabEpreuveId,
                               @PathVariable(name = "nbr") int nbr) {
        jourEtabEpreuveService.createNbrJuriesById(jourId, etabEpreuveId, nbr);
    }

    @PostMapping("/api/epreuve/jour/{id}/{value}")
    public void changeDispoJour(@PathVariable (name = "id") Long id,@PathVariable(name = "value") Boolean value){
        jourService.updateJourById(id,value);
    }
}
