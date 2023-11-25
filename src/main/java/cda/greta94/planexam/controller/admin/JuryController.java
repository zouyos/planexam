package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dao.EtabEpreuveRepository;
import cda.greta94.planexam.dao.JourEtabEpreuveRepository;
import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dto.JuryDto;
import cda.greta94.planexam.service.JourEtabEpreuveService;
import cda.greta94.planexam.service.JuryService;
import cda.greta94.planexam.service.ProfesseurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class JuryController {

    private JuryService juryService;
    private ProfesseurRepository professeurRepository;
    private EtabEpreuveRepository etabEpreuveRepository;
    private JourEtabEpreuveRepository jourEtabEpreuveRepository;

    public JuryController(JuryService juryService,
                          ProfesseurRepository professeurRepository,
                          EtabEpreuveRepository etabEpreuveRepository,
                          JourEtabEpreuveRepository jourEtabEpreuveRepository) {
        this.juryService = juryService;
        this.professeurRepository = professeurRepository;
        this.etabEpreuveRepository = etabEpreuveRepository;
        this.jourEtabEpreuveRepository = jourEtabEpreuveRepository;
    }

    @GetMapping("/jurys")
    public String index(Model model) {
        model.addAttribute("jurys", juryService.getAll());
        model.addAttribute("profs", professeurRepository.findAll());
        model.addAttribute("jees", jourEtabEpreuveRepository.findAll());
        return "/admin/jury/index";
    }

    @GetMapping("/jury/create")
    public String create(@ModelAttribute(name = "jury") JuryDto juryDto, Model model) {
        model.addAttribute("profsSISR", professeurRepository.findBySpecialite_Libelle("SISR"));
        model.addAttribute("profsSLAM", professeurRepository.findBySpecialite_IdGreaterThan(1L));
        model.addAttribute("jees", jourEtabEpreuveRepository.findAll());
        model.addAttribute("etabs", etabEpreuveRepository.findDistinctByJourEtabEpreuveList_NbrJuryGreaterThan(0));
        return "admin/jury/form";
    }

    @GetMapping("/jury/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        JuryDto juryDto = juryService.getJuryDtoById(id);
        model.addAttribute("jury", juryDto);
        model.addAttribute("profsSISR", professeurRepository.findBySpecialite_Libelle("SISR"));
        model.addAttribute("profsSLAM", professeurRepository.findBySpecialite_IdGreaterThan(1L));
        model.addAttribute("jees", jourEtabEpreuveRepository.findAll());
        model.addAttribute("etabs", etabEpreuveRepository.findDistinctByJourEtabEpreuveList_NbrJuryGreaterThan(0));
        return "admin/jury/form";
    }

    @PostMapping("/jury")
    public String addUpdateJury(@Valid @ModelAttribute JuryDto juryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/jury/form";
        }
        juryService.saveJuryFromDto(juryDto);
        return "redirect:/admin/jurys";
    }

    @PostMapping("/jury/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        juryService.delete(id);
        return "redirect:/admin/jurys";
    }
}
