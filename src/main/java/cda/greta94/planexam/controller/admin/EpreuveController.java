package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.EpreuveDto;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.JourService;
import cda.greta94.planexam.service.EpreuveService;
import cda.greta94.planexam.service.ProfesseurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class EpreuveController {

  private EpreuveService epreuveService;

  private JourService jourService;

  private EtablissementService etablissementService;

  public EpreuveController(EpreuveService epreuveService, JourService jourService, EtablissementService etablissementService) {
    this.epreuveService = epreuveService;
    this.jourService = jourService;
    this.etablissementService = etablissementService;
  }

  @GetMapping("/epreuves")
  public String index(Model model) {
    model.addAttribute("epreuves", epreuveService.getAll());
    return "admin/epreuve/index";
  }

  @GetMapping("/epreuve/create")
  public String create(@ModelAttribute EpreuveDto epreuveDto) { return "admin/epreuve/form"; }

  @GetMapping("/epreuve/show/{id}")
  public String show(@PathVariable("id") Long id, Model model) {
    EpreuveDto epreuveDto = epreuveService.findEpreuveDtoById(id);
    model.addAttribute("epreuveDto", epreuveDto);
    model.addAttribute("jours", jourService.getAll());
    model.addAttribute("etabs", etablissementService.getAll());
    return "admin/epreuve/show";
  }

  @PostMapping("/epreuve/jour/{id}/{value}")
  public void changeDispoJour(@PathVariable (name = "id") Long id,@PathVariable(name = "value") Boolean value){
    jourService.updateJourById(value,id);
  }

  @GetMapping("/epreuve/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    EpreuveDto epreuveDto = epreuveService.findEpreuveDtoById(id);
    model.addAttribute("epreuveDto", epreuveDto);
    return "admin/epreuve/form";
  }

  @PostMapping("/epreuve")
  public String addUpdateSession(@Valid @ModelAttribute EpreuveDto epreuveDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/epreuve/form";
    }
    epreuveService.saveEpreuveFromSessionDto(epreuveDto);
    return "redirect:/admin/epreuves";
  }

  @PostMapping("/epreuve/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    epreuveService.delete(id);
    return "redirect:/admin/epreuves";
  }
}
