package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.JourDto;
import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.service.JourService;
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

  private JourService jourService;

  public SessionController(SessionService sessionService, JourService jourService) {
    this.sessionService = sessionService;
    this.jourService = jourService;
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
    model.addAttribute("jours", jourService.getAll());
    return "admin/session/show";
  }

  @PostMapping("/session/jour/{id}/{value}")
  public void changeDispoJour(@PathVariable (name = "id") Long id,@PathVariable(name = "value") Boolean value){
    jourService.updateJourById(value,id);
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
  public String createJour(@ModelAttribute(name="jour") JourDto jourDto, Model model) {
    model.addAttribute("sessions", sessionService.getAll());
    return "admin/jour/form";
  }

  @PostMapping("/jour")
  public String createJour(@Valid @ModelAttribute JourDto jourDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/jour/form";
    }
    jourService.saveJourFromDto(jourDto);
    //TODO: rediriger vers la session associée
    return "redirect:/admin/session/show/1";
  }

  @PostMapping("/jour/delete/{id}")
  public String deleteJour(@PathVariable("id") Long id) {
    jourService.delete(id);
    return "redirect:/admin/session/show/1";
  }
}
