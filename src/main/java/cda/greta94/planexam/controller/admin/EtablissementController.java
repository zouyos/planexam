package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.VilleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class EtablissementController {

  private Logger logger = LoggerFactory.getLogger(EtablissementController.class);

  private EtablissementService etablissementService;
  private VilleService villeService;

  public EtablissementController(EtablissementService etablissementService, VilleService villeService) {
    this.etablissementService = etablissementService;
    this.villeService = villeService;
  }

  @GetMapping(value = "/etablissements")
  public String index(Model model, @RequestParam(defaultValue = "") String nom, @RequestParam(defaultValue = "0") int page) {
    Pageable pageable = PageRequest.of(page, 20);
    Page<Etablissement> etablissements = etablissementService.getPageEtRecherche(nom, pageable);
    model.addAttribute("pageNumber", page);
    model.addAttribute("pageEtablissements", etablissements);
    return "admin/etablissement/index";
  }

  @GetMapping(value = "/etablissement")
  public String pushFormEtab(@ModelAttribute EtablissementDto etablissementDto, Model model) {
    model.addAttribute("villes", villeService.getAll());
    return "admin/etablissement/form";
  }

  @PostMapping(value = "/etablissement")
  public String addUpdateEtab(@Valid @ModelAttribute EtablissementDto etablissementDto, BindingResult bindingResult) {
    logger.info("addUpdateEtab : (" + etablissementDto + ")");
    logger.info("hasErrors = " + bindingResult.hasErrors());

    if (bindingResult.hasErrors()) {
      return "admin/etablissement/form";
    }
    etablissementService.saveEtablissementFromEtablissementDto(etablissementDto);
    return "redirect:/admin/etablissements";
  }

  @GetMapping(value = "/etablissement/edit/{id}")
  public String pushFormEtab(@PathVariable("id") long id, Model model) {
    EtablissementDto etablissementDto = etablissementService.findEtablissementDtoById(id);
    model.addAttribute("etablissementDto", etablissementDto);
    model.addAttribute("villes", villeService.getAll());
    return "admin/etablissement/form";
  }

  @PostMapping(value = "/etablissement/delete/{id}")
  public String delete(@PathVariable(name = "id") Long id) {
    etablissementService.delete(id);
    return "redirect:/admin/etablissements";
  }
}
