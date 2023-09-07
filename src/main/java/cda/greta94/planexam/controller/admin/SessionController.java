package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.JourPassageDto;
import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.service.JourPassageService;
import cda.greta94.planexam.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SessionController {

  private SessionService sessionService;

  private JourPassageService jourPassageService;

  public SessionController(SessionService sessionService, JourPassageService jourPassageService) {
    this.sessionService = sessionService;
    this.jourPassageService = jourPassageService;
  }

  @GetMapping("/sessions")
  public String index(Model model) {
    model.addAttribute("sessions", sessionService.getAll());
    return "admin/session/index";
  }

  @GetMapping("/session/create")
  public String create(@ModelAttribute SessionE5Dto sessionE5Dto) { return "admin/session/form"; }

  @GetMapping("/session/show/{id}")
  public String show(@PathVariable("id") Long id, Model model) {
    SessionE5Dto sessionE5Dto = sessionService.findSessionDtoById(id);
    model.addAttribute("sessionE5Dto", sessionE5Dto);
    model.addAttribute("jours", jourPassageService.getAll());
    return "admin/session/show";
  }

  @GetMapping("/session/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    SessionE5Dto sessionE5Dto = sessionService.findSessionDtoById(id);
    model.addAttribute("sessionE5Dto", sessionE5Dto);
    return "admin/session/form";
  }

  @PostMapping("/session")
  public String addUpdateSession(@Valid @ModelAttribute SessionE5Dto sessionE5Dto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/session/form";
    }
    sessionService.saveSessionFromSessionDto(sessionE5Dto);
    return "redirect:/admin/sessions";
  }

  @PostMapping("/session/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    sessionService.delete(id);
    return "redirect:/admin/sessions";
  }

  //Jour Passage

  @GetMapping("/jour/create")
  public String createJour(@ModelAttribute(name="jour") JourPassageDto jourPassageDto, Model model) {
    model.addAttribute("sessions", sessionService.getAll());
    return "admin/jourPassage/form";
  }

  @PostMapping("/jour")
  public String createJour(@Valid @ModelAttribute JourPassageDto jourPassageDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/jourPassage/form";
    }
    jourPassageService.saveJourPassageFromDto(jourPassageDto);
    //TODO: rediriger vers la session associée
    return "redirect:/admin/session/show/1";
  }

  @PostMapping("/jour/delete/{id}")
  public String deleteJour(@PathVariable("id") Long id) {
    jourPassageService.delete(id);
    return "redirect:/admin/session/show/1";
  }
}
