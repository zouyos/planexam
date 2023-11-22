package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.AuthRequestDto;
import cda.greta94.planexam.dto.AuthResponseDto;
import cda.greta94.planexam.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/**", methods = {RequestMethod.GET, RequestMethod.POST})
public class ApiController {

    private AuthService authService;

    private EtablissementService etablissementService;

    private JourService jourService;

    private EtabEpreuveService etabEpreuveService;

    private JourEtabEpreuveService jourEtabEpreuveService;

    public ApiController(
        EtablissementService etablissementService,
        JourService jourService,
        EtabEpreuveService etabEpreuveService,
        JourEtabEpreuveService jourEtabEpreuveService,
        AuthService authService
    ) {
        this.etablissementService = etablissementService;
        this.jourService = jourService;
        this.etabEpreuveService = etabEpreuveService;
        this.jourEtabEpreuveService = jourEtabEpreuveService;
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto dto) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }

    @GetMapping("/api/etablissement/ponctuel/{id}/{value}")
    public void updatePonctuel(@PathVariable(name = "id") Long id,
                               @PathVariable(name = "value") Boolean value,
                               HttpServletResponse httpResponse)
    {
        httpResponse.setHeader("Set-Cookie", "");
        etablissementService.updatePonctuelById(id, value);
    }

    @GetMapping("/api/epreuve/jour/{id}/{value}")
    public void changeDispoJour(@PathVariable (name = "id") Long id,
                                @PathVariable(name = "value") Boolean value,
                                HttpServletResponse httpResponse)
    {
        httpResponse.setHeader("Set-Cookie", "");
        jourService.updateJourById(id,value);
    }

    @GetMapping("/api/epreuve/nbr-candidats/{etabEpreuveId}/{nbr}")
    public void updateNbrJurys(@PathVariable(name = "etabEpreuveId") Long etabEpreuveId,
                               @PathVariable(name = "nbr") int nbr,
                               HttpServletResponse httpResponse)
    {
        httpResponse.setHeader("Set-Cookie", "");
        etabEpreuveService.createNbrCandidats(etabEpreuveId, nbr);
    }

    @GetMapping("/api/epreuve/nbr-juries/{jourId}/{etabEpreuveId}/{nbr}")
    public void updateNbrJurys(@PathVariable(name = "jourId") Long jourId,
                               @PathVariable(name = "etabEpreuveId") Long etabEpreuveId,
                               @PathVariable(name = "nbr") int nbr,
                               HttpServletResponse httpResponse)
    {
        httpResponse.setHeader("Set-Cookie", "");
        jourEtabEpreuveService.createNbrJuriesById(jourId, etabEpreuveId, nbr);
    }
}
