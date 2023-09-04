package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.VilleDto;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.ProfesseurService;
import cda.greta94.planexam.service.VilleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class VilleController {
    private VilleService villeService;
    private EtablissementService etablissementService;
    private ProfesseurService professeurService;

    public VilleController(VilleService villeService, EtablissementService etablissementService, ProfesseurService professeurService) {
        this.villeService = villeService;
        this.etablissementService = etablissementService;
        this.professeurService = professeurService;
    }

    @GetMapping("/villes")
    public String index(Model model) {
        model.addAttribute("villes", villeService.getAll());
        model.addAttribute("etabs", etablissementService.getAll());
        return "admin/ville/index";
    }

    @GetMapping("/ville/create")
    public String create(@ModelAttribute(name = "ville")VilleDto villeDto, Model model) {
        model.addAttribute("villes", villeService.getAll());
        return "admin/ville/form";
    }

    @GetMapping("/ville/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        VilleDto villeDto = villeService.getVilleDtoById(id);
        model.addAttribute("ville", villeDto);
        return "admin/ville/form";
    }

    @PostMapping("/ville")
    public String addUpdate(@Valid @ModelAttribute VilleDto villeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/ville/form";
        }
        villeService.saveVilleFromDto(villeDto);
        return "redirect:/admin/villes";
    }

    @PostMapping("/ville/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        villeService.delete(id);
        return "redirect:/admin/villes";
    }
}
