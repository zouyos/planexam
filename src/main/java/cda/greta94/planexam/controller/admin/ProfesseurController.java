package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.ProfesseurDto;
import cda.greta94.planexam.dto.SpecialiteDto;
import cda.greta94.planexam.service.ProfesseurService;
import cda.greta94.planexam.service.SpecialiteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ProfesseurController {
  private ProfesseurService professeurService;
  private SpecialiteService specialiteService;

  public ProfesseurController(ProfesseurService professeurService, SpecialiteService specialiteService) {
    this.professeurService = professeurService;
    this.specialiteService = specialiteService;
  }

  @GetMapping("/enseignants")
  public String index(Model model) {
    model.addAttribute("profs", professeurService.getAll());
    return "admin/professeur/index";
  }

  @GetMapping("/enseignant/create")
  public String create(@ModelAttribute(name="prof") ProfesseurDto professeurDto) { return "admin/professeur/form"; }

  @GetMapping("/enseignant/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    ProfesseurDto professeurDto = professeurService.findProfDtoById(id);
    model.addAttribute("prof", professeurDto);
    return "admin/professeur/form";
  }

  @PostMapping("/enseignant")
  public String addUpdateProf(@Valid @ModelAttribute ProfesseurDto professeurDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/professeur/form";
    }
    professeurService.saveProfFromDto(professeurDto);
    return "redirect:/admin/enseignants";
  }

  @PostMapping("/enseignant/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    professeurService.delete(id);
    return "redirect:/admin/enseignants";
  }

  //Specialite

  @GetMapping("/specialites")
  public String indexS(Model model) {
    model.addAttribute("specs", specialiteService.getAll());
    return "admin/specialite/index";
  }

  @GetMapping("/specialite/create")
  public String create(@ModelAttribute(name="spec") SpecialiteDto specialiteDto) { return "admin/specialite/form"; }

  @GetMapping("/specialite/edit/{id}")
  public String editS(@PathVariable("id") Long id, Model model) {
    SpecialiteDto specialiteDto = specialiteService.findSpecialiteDtoById(id);
    model.addAttribute("spec", specialiteDto);
    return "admin/specialite/form";
  }

  @PostMapping("/specialite")
  public String addUpdateProf(@Valid @ModelAttribute SpecialiteDto specialiteDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/specialite/form";
    }
    specialiteService.saveSpecialiteFromDto(specialiteDto);
    return "redirect:/admin/specialites";
  }

  @PostMapping("/specialite/delete/{id}")
  public String deleteS(@PathVariable("id") Long id) {
    specialiteService.delete(id);
    return "redirect:/admin/specialites";
  }
}
