package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dao.JourEtabEpreuveRepository;
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

  private JourEtabEpreuveRepository jourEtabEpreuveRepository;

  private EtabEpreuveService etabEpreuveService;

  private JuryService juryService;

  private ProfesseurService professeurService;

  public EpreuveController(EpreuveService epreuveService,
                           JourService jourService,
                           EtablissementService etablissementService,
                           JourEtabEpreuveRepository jourEtabEpreuveRepository,
                           EtabEpreuveService etabEpreuveService,
                           JuryService juryService,
                           ProfesseurService professeurService
  ) {
    this.epreuveService = epreuveService;
    this.jourService = jourService;
    this.etablissementService = etablissementService;
    this.jourEtabEpreuveRepository = jourEtabEpreuveRepository;
    this.etabEpreuveService = etabEpreuveService;
    this.juryService = juryService;
    this.professeurService = professeurService;
  }

  @GetMapping("/epreuves")
  public String index(Model model) {
    model.addAttribute("epreuves", epreuveService.getAll());
    model.addAttribute("profs", professeurService.getAll());
    return "admin/epreuve/index";
  }

  @GetMapping("/epreuve/create")
  public String create(@ModelAttribute EpreuveDto epreuveDto) { return "admin/epreuve/form"; }

  @GetMapping("/epreuve/nbr-jury/{id}")
  public String nbrJury(@PathVariable("id") Long id, Model model) {
    EpreuveDto epreuveDto = epreuveService.findEpreuveDtoById(id);
    model.addAttribute("epreuveDto", epreuveDto);
    model.addAttribute("jours", jourService.findByEpreuve(id));
    model.addAttribute("etabEpreuveList", etabEpreuveService.getByIdEpreuveAndPonctuel(id));
    return "admin/epreuve/nbrJury";
  }

  @GetMapping("/epreuve/jury/{id}")
  public String jurys(@PathVariable("id") Long id, Model model) {
    EpreuveDto epreuveDto = epreuveService.findEpreuveDtoById(id);
    model.addAttribute("epreuveDto", epreuveDto);
    model.addAttribute("jours", jourService.findByEpreuve(id));
    model.addAttribute("etabEpreuveList", etabEpreuveService.getByIdEpreuveAndPonctuel(id));
    model.addAttribute("juries", juryService.getAll());
    model.addAttribute("profs", professeurService.getAll());
    return "admin/epreuve/jury";
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
      return "redirect:/admin/epreuve/import/"+idEpreuve;
    }
    try {
      epreuveService.importEtablissementFromCSVFile(file, idEpreuve);
    } catch (Exception e) {
      redirAttrs.addFlashAttribute("errorMessage", e.getMessage());
      return "redirect:/admin/epreuve/import/"+idEpreuve;
    }
    // ok
    redirAttrs.addFlashAttribute("successMessage", "Importation réussie ! \n" +
            "Après avoir défini les nombres de jurys, passez à l'étape 3 sur la page de gestion des épreuves");
    return "redirect:/admin/epreuve/nbr-jury/"+idEpreuve;
  }

  @GetMapping(value = "epreuve/enseignants/import")
  public String formImportCSV() { return "admin/epreuve/formImportProfs"; }

  @PostMapping(value = "epreuve/enseignants/import")
  public String importCSV(@RequestParam("file")MultipartFile file, RedirectAttributes redirAttrs) {
    if (file.isEmpty()) {
      redirAttrs.addFlashAttribute("errorMessage", "Please select a file to upload");
      return "redirect:/admin/epreuve/enseignants/import";
    }
    try {
      epreuveService.importProfFromCSV(file);
    } catch(Exception e) {
      redirAttrs.addFlashAttribute("errorMessage", e.getMessage());
      return "redirect:/admin/epreuve/enseignants/import";
    }
    redirAttrs.addFlashAttribute("successMessage", "Importation réussie ! Vous pouvez passer à l'étape 4");
    return "redirect:/admin/epreuves";
  }

  @PostMapping("/epreuve/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    epreuveService.delete(id);
    return "redirect:/admin/epreuves";
  }
}
