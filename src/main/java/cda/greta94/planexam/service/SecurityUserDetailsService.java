package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.UtilisateurRepository;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

  private UtilisateurRepository utilisateurRepository;
  private PasswordEncoder encoder;

  @Autowired
  public SecurityUserDetailsService(UtilisateurRepository utilisateurRepository, PasswordEncoder encoder) {
    this.utilisateurRepository = utilisateurRepository;
    this.encoder = encoder;
  }

  /***
   * Une méthode qui prend en param un nom d'utilisateur ou email qui provient du formulaire de login.
   * Et retourne un objet User (class provenant de Security) avec un username, password (encodé), une liste de permissions
   * @param username pseudo ou email du form de login
   * @return  Un objet User (qui implement l'interface UserDetails)
   * @throws UsernameNotFoundException
   */
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //Etape 1 : obtenir les infos de l'utilisateur cad un objet Utilisateur
    Utilisateur utilisateur = utilisateurRepository.findByEmail(username);
    //Etape 2 : Verification que on a bien un utilisateur ( cas si l'email n'est pas correct)
    if(utilisateur == null) {
      throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
    }
    //Etape 3 : Recupere le nom du role
    String nomRole = utilisateur.getRole().getNom();
    //Etape 3 bis (cas avec des Utilisateurs dans des classe séparées)
    //TODO
    //String nomRole = utilisateur.getClass().getSimpleName();
    //Etape 4 je consitue un liste de permissions
    Set<GrantedAuthority> permissions = new HashSet<>();
    //Etape 5 J'ajoute le nom du role dans la liste de permissions.
    permissions.add(new SimpleGrantedAuthority(nomRole));
    //Etape 6 je créer un objet User
    User user = new User(utilisateur.getEmail(), utilisateur.getMdp(), permissions);
    return user;
  }
}
