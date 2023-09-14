package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.JuryDto;
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
    private ProfesseurService professeurService;

    public JuryController(JuryService juryService, ProfesseurService professeurService) {
        this.juryService = juryService;
        this.professeurService = professeurService;
    }

    @GetMapping("/jurys")
    public String index(Model model) {
        model.addAttribute("jurys", juryService.getAll());
        return "/admin/jury/index";
    }

    @GetMapping("/jury/create")
    public String create(@ModelAttribute(name = "jury") JuryDto juryDto, Model model) {
        model.addAttribute("profs", professeurService.getAll());
        return "admin/jury/form";
    }

    @GetMapping("/jury/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        JuryDto juryDto = juryService.getJuryDtoById(id);
        model.addAttribute("jury", juryDto);
        model.addAttribute("profs", professeurService.getAll());
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
