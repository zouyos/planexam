package cda.greta94.planexam.config;

import cda.greta94.planexam.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService; // Service pour la gestion des JWT

  @Autowired
  private UserDetailsService userDetailsService; // Service pour la gestion des détails des utilisateurs

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // Vérifie si l'utilisateur n'est pas déjà authentifié
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      final String authorization = request.getHeader("Authorization");
      // Récupère l'en-tête "Authorization" et vérifie s'il commence par "Bearer"
      if (authorization != null && authorization.startsWith("Bearer ")) {
        // Extrait le token JWT en retirant "Bearer "
        final String token = authorization.substring(7);
        // Analyse le JWT pour obtenir ses revendications
        final Claims claims = jwtService.getClaims(token);
        // Vérifie si le JWT n'a pas expiré en comparant avec la date actuelle
        if (claims.getExpiration().after(new Date())) {
          // Récupère le nom d'utilisateur à partir du JWT
          final String username = claims.getSubject();
          // Charge les détails de l'utilisateur à partir du service UserDetailsService
          final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          // Crée un objet d'authentification UsernamePasswordAuthenticationToken
          final UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities()
              );
          // Ajoute les détails de l'authentification basés sur la requête
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          // Définit l'authentification dans le contexte de sécurité
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    }
    // Poursuit la chaîne de filtres de sécurité
    filterChain.doFilter(request, response);
  }
}
