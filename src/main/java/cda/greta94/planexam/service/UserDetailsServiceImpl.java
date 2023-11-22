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
public class UserDetailsServiceImpl implements UserDetailsService {

  private UtilisateurRepository utilisateurRepository;
  private PasswordEncoder encoder;

  @Autowired
  public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder encoder) {
    this.utilisateurRepository = utilisateurRepository;
    this.encoder = encoder;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Utilisateur utilisateur = utilisateurRepository.findByEmail(username);
      String nomRole = utilisateur.getRole().getNom();
      Set<GrantedAuthority> permissions = new HashSet<>();
      permissions.add(new SimpleGrantedAuthority(nomRole));
      User user = new User(utilisateur.getEmail(), utilisateur.getMdp(), permissions);
      return user;
    } catch (Exception e) {
      throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
    }
  }
}
