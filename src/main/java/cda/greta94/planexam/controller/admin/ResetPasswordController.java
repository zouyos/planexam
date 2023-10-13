package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.PasswordDto;
import cda.greta94.planexam.model.JetonResetMdp;
import cda.greta94.planexam.model.Utilisateur;
import cda.greta94.planexam.service.JavaSenderMail;
import cda.greta94.planexam.service.JetonResetMdpService;
import cda.greta94.planexam.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResetPasswordController {
    private JetonResetMdpService tokenService;

    private JavaSenderMail javaSenderMail;

    private UtilisateurService utilisateurService;

    @Autowired
    public ResetPasswordController(JetonResetMdpService tokenService, JavaSenderMail javaSenderMail, UtilisateurService utilisateurService) {
        this.tokenService = tokenService;
        this.javaSenderMail = javaSenderMail;
        this.utilisateurService = utilisateurService;
    }

    /**
     * Formulaire en cas d'oubli
     * @return
     */
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "admin/security/forgotPassword";
    }

    /**
     * Traitement du formulaire
     * @param email
     * @return
     */
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            // Générez un jeton de réinitialisation de mot de passe
            String token = tokenService.createToken(utilisateur).getToken();
            // Envoyez le jeton au courriel de l'utilisateur et incluez un lien pour réinitialiser le mot de passe
            javaSenderMail.sendPasswordResetEmail(utilisateur.getEmail(), token);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Email envoyé avec succès");
        return "redirect:/forgot-password?success";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {
        JetonResetMdp resetToken = tokenService.findByToken(token);
        if (resetToken != null) {
            PasswordDto dto = new PasswordDto(token);
            model.addAttribute("passwordDto", dto);
            return "admin/security/resetMdp";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Token invalide");
        return "redirect:/forgot-password?invalidToken";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@Valid @ModelAttribute PasswordDto passwordDto, BindingResult bindingResult, RedirectAttributes redirectAttributes)  {
        if (bindingResult.hasErrors()) {
            return "admin/security/resetMdp";
        }
        JetonResetMdp resetToken = tokenService.findByToken(passwordDto.getToken());
        if (resetToken != null) {
            //TODO verification de l'expiration du token
            Utilisateur utilisateur = utilisateurService.convertToEntity(passwordDto);
            utilisateurService.saveNewPassword(utilisateur);
            tokenService.deleteToken(resetToken);
            redirectAttributes.addFlashAttribute("successMessage", "Votre mot de passe a bien été modifié");
            return "redirect:/login?passwordResetSuccess";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors du renouvellement du mot de passe");
        return "redirect:/login?passwordResetError";
    }
}
