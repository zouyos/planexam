package cda.greta94.planexam.service;

import cda.greta94.planexam.dto.AuthRequestDto;
import cda.greta94.planexam.dto.AuthResponseDto;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UtilisateurService utilisateurService; // Service pour gérer les informations des utilisateurs

  @Autowired
  private PasswordEncoder passwordEncoder; // Utilitaire pour encoder les mots de passe

  @Autowired
  private JwtService jwtService; // Service pour la gestion des JWT (JSON Web Tokens)

  @Autowired
  private AuthenticationManager authenticationManager; // Gestionnaire d'authentification

  public AuthResponseDto authenticate(AuthRequestDto dto) {
    // Méthode d'authentification d'un utilisateur existant

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
    // Utilise le gestionnaire d'authentification pour valider les informations d'identification

    final Utilisateur utilisateur = utilisateurService.findByEmail(dto.getEmail());
    // Récupère l'utilisateur correspondant à l'e-mail fourni

    return new AuthResponseDto(jwtService.generateToken(utilisateur.getEmail()));
    // Génère un JWT pour l'utilisateur authentifié et le renvoie dans une réponse
  }
}
