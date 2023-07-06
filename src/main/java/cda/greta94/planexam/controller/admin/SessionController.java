package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.SessionE5Dto;
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

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @GetMapping("/sessions")
  public String index(Model model) {
    model.addAttribute("sessions", sessionService.getAll());
    return "admin/session/index";
  }

  @GetMapping("/session/create")
  public String create(@ModelAttribute SessionE5Dto sessionE5Dto) { return "admin/session/form"; }

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
}
