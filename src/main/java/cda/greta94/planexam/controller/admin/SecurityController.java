package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.UtilisateurDto;
import cda.greta94.planexam.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecurityController {
  private UtilisateurService utilisateurService;

  @Autowired
  public SecurityController(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @GetMapping("/login")
  public String login(){
    return "admin/security/login";
  }

  @GetMapping("/inscription")
  public String inscription(Model model) {
    model.addAttribute("utilisateur", new UtilisateurDto());
    return "admin/security/inscription";
  }

  @PostMapping("/inscription")
  public String traitementInscription(@Valid @ModelAttribute UtilisateurDto utilisateur, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      return "redirect:/inscription";
    }
    utilisateurService.inscrireClient(utilisateur);
    redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie");
    return "redirect:/login";
  }

  @GetMapping("/prof/dashboard")
  public String profDash(){
    return "admin/security/prof/dashboard";
  }
}
