package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.RoleRepository;
import cda.greta94.planexam.dao.UtilisateurRepository;
import cda.greta94.planexam.dto.PasswordDto;
import cda.greta94.planexam.dto.UtilisateurDto;
import cda.greta94.planexam.exception.DuplicationException;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UtilisateurService {
  private UtilisateurRepository utilisateurRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder encoder;

  @Autowired
  public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
    this.utilisateurRepository = utilisateurRepository;
    this.roleRepository= roleRepository;
    this.encoder=encoder;
  }

  public Utilisateur convertToEntity(UtilisateurDto dto){
    Utilisateur utilisateur;
    if (dto.getId() == null) {
      utilisateur = new Utilisateur();
    } else {
      utilisateur = utilisateurRepository.findById(dto.getId()).orElse(new Utilisateur());
    }
    checkEmailDuplication(utilisateur);
    utilisateur.setEmail(dto.getEmail());
    utilisateur.setMdp(dto.getMdp1NonEncode());
    utilisateur.setRole(dto.getRole());
    return utilisateur;
  }

  public Utilisateur inscrireUser(UtilisateurDto dto) {
    Utilisateur user = this.convertToEntity(dto);
    checkEmailDuplication(user);
    user.setRole(roleRepository.findById(2l).get());
    user.setMdp(encoder.encode(user.getMdp()));
    return utilisateurRepository.save(user);
  }

  public Utilisateur saveNewPassword(Utilisateur user){
    if (user.getId() != null) {
      user.setMdp(encoder.encode(user.getMdp()));
      return utilisateurRepository.save(user);
    }
    throw new NoSuchElementException();
  }

  public Utilisateur convertToEntity(PasswordDto dto){
    Utilisateur utilisateur;
    if(dto.getToken() == null){
      throw new NoSuchElementException();
    }
    else {
      utilisateur = utilisateurRepository.findByJetonResetMdps_TokenIgnoreCase(dto.getToken()).orElseThrow();
      utilisateur.setMdp(dto.getMdp1());
    }
    return utilisateur;
  }

  public Utilisateur findByEmail(String email){
    return this.utilisateurRepository.findByEmail(email);
  }

  private void checkEmailDuplication(Utilisateur utilisateur) {
    final String email = utilisateur.getEmail();
    if (email != null && email.length() > 0) {
      final Long id = utilisateur.getId();
      final Utilisateur p = utilisateurRepository.findByEmail(email);
      if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
        throw new DuplicationException("Email duplication: " + email);
      }
    }
  }

  public Utilisateur create(Utilisateur utilisateur) {
    utilisateur.setId(null);
    checkEmailDuplication(utilisateur);
    return utilisateurRepository.save(utilisateur);
  }
}
