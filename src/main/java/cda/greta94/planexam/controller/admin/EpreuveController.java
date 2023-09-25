package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dao.NbrJuryRepository;
import cda.greta94.planexam.dto.EpreuveDto;
import cda.greta94.planexam.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class EpreuveController {

  private EpreuveService epreuveService;

  private JourService jourService;

  private EtablissementService etablissementService;

  private NbrJuryRepository nbrJuryRepository;

  private EtabEpreuveService etabEpreuveService;

  public EpreuveController(EpreuveService epreuveService, JourService jourService, EtablissementService etablissementService, NbrJuryRepository nbrJuryRepository, EtabEpreuveService etabEpreuveService) {
    this.epreuveService = epreuveService;
    this.jourService = jourService;
    this.etablissementService = etablissementService;
    this.nbrJuryRepository = nbrJuryRepository;
    this.etabEpreuveService = etabEpreuveService;
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
    model.addAttribute("jours", jourService.findByEpreuve(id));
    model.addAttribute("etabEpreuveList", etabEpreuveService.getByIdEpreuveAndPonctuel(id));
    return "admin/epreuve/show";
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
    epreuveService.saveEpreuveFromEpreuveDto(epreuveDto);
    return "redirect:/admin/epreuves";
  }

  @GetMapping(value = "/epreuve/import/{idEpreuve}")
  public String formImportCSV(@PathVariable Long idEpreuve, Model model) {
    model.addAttribute("idEpreuve", idEpreuve);
    return "admin/epreuve/formImportCSV";
  }

  @PostMapping(value = "/epreuve/import")
  public String importCSV(
          @RequestParam("file") MultipartFile file,
          @RequestParam("idEpreuve") Long idEpreuve,
          RedirectAttributes redirAttrs)
  {
    if (file.isEmpty()) {
      // PATTERN PRG
      redirAttrs.addFlashAttribute("errorMessage", "Please select a file to upload");
      return "redirect:/admin/epreuve/import";
    }
    try {
      epreuveService.importEtablissementFromCSVFile(file, idEpreuve);
    } catch (Exception e) {
      redirAttrs.addFlashAttribute("errorMessage", e.getMessage());
      return "redirect:/admin/epreuve/import";
    }
    // ok
    redirAttrs.addFlashAttribute("successMessage", "Importation réussie !");
    return "redirect:/admin/epreuve/show/"+idEpreuve;
  }

  @PostMapping("/epreuve/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    epreuveService.delete(id);
    return "redirect:/admin/epreuves";
  }
}
